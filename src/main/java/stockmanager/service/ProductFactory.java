package stockmanager.service;

import stockmanager.domain.ProductionOrder;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.Warehouse;

import java.util.List;

public enum ProductFactory {
    INSTANCE;

    private Warehouse warehouse = Warehouse.INSTANCE;

    private ProductFactory() {
        //TODO delete
        warehouse.add(Corn.INSTANCE, 100);
        warehouse.add(Wheat.INSTANCE, 50);
    }

    private void phase(List<ProductionOrder> productionOrders, int phaseIndex) {
        for (ProductionOrder productionOrder : productionOrders) {
            Product product = productionOrder.getProduct();
            long wantedQty = productionOrder.getQty();
            if (product.getResourceByPreference(phaseIndex) instanceof Wheat) {
                double producableQty = Math.floor(warehouse.getQty(Wheat.INSTANCE) / product.getCostIn(Wheat.INSTANCE));
                if (wantedQty <= producableQty) {
                    warehouse.subtract((Wheat.INSTANCE), (long) Math.ceil(wantedQty * product.getCostIn(Wheat.INSTANCE)));
                    warehouse.add(product, wantedQty);
                } else {
                    warehouse.subtract((Wheat.INSTANCE), (long) Math.ceil(producableQty * product.getCostIn(Wheat.INSTANCE)));
                    warehouse.add(product, (long) producableQty);
                }
            } else if (product.getResourceByPreference(phaseIndex) instanceof Corn) {
                double producableQty = Math.floor(warehouse.getQty(Corn.INSTANCE) / product.getCostIn(Corn.INSTANCE));
                if (wantedQty + (4 - wantedQty % 4) <= producableQty) {
                    warehouse.subtract((Corn.INSTANCE), (long) Math.ceil(wantedQty * product.getCostIn(Corn.INSTANCE)));
                    warehouse.add(product, wantedQty);
                } else {
                    warehouse.subtract((Corn.INSTANCE), (long) Math.ceil(producableQty - producableQty % 4 * product.getCostIn(Corn.INSTANCE)));
                    warehouse.add(product, (long) producableQty);
                }
                /*
                Vizsgálat: [tbp + (4 - tbp % 4)] <= truncate(corn_stock / cost_in_corn)
				Ha teljesül, akkor legyártunk tbp + (4 - tbp % 4) mennyiséget.
				Ha nem, akkor csak [truncate(corn_stock / cost_in_corn) - truncate(corn_stock / cost_in_corn) % 4] mennyiséget gyártunk.


				2.	Secondary
				tbp = approx - stock;
		        A legyártani kívánt mennyiség kiszámolása változatlan marad, mivel a stock értéke az 1. fázisban már frissült.
		        A módszer csak annyiban tér el, hogy másodlagos nyersanyagot használunk.

	            3.	Fill Buffer
		        tbp = stock_max - stock; tbp >= 0; Negatív érték esetén tbp = 0.
		        A módszer megegyezik az 1. fázissal.
                 */
            }
        }
    }

    public void produce(List<ProductionOrder> productionOrders) {
        for (int phaseIndex = 0; phaseIndex < 2; phaseIndex++) {
            phase(productionOrders, phaseIndex);
        }
    }

    public void fillBuffer() {
        long toBeProduced = 17;
        long wheatInStock = resourceStock.get(Wheat.INSTANCE).getQty();
        double costInResource = toBeProduced * Sheep.INSTANCE.getCostIn(Sheep.INSTANCE.getResourceByPreference(0));
        if (costInResource <= wheatInStock)
            resourceStock.get(Wheat.INSTANCE).subtract((long) Math.ceil(costInResource));
        else
            resourceStock.get(Wheat.INSTANCE).subtract(calculateFeasibleAmount(wheatInStock, Sheep.INSTANCE, Wheat.INSTANCE));

    }

    private long calculateFeasibleAmount(long inStockQty, Product producibleProduct, Resource fromWhat) {
        return (long) Math.floor(inStockQty / producibleProduct.getCostIn(fromWhat));
    }
}
