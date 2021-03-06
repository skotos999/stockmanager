package stockmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stockmanager.domain.products.Product;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.Warehouse;

import java.util.List;

public enum ProductFactory {
    INSTANCE;
    private static Logger logger = LoggerFactory.getLogger(ProductFactory.class);
    private Warehouse warehouse = Warehouse.INSTANCE;

    private ProductFactory() {
    }

    public void phase(List<ProductionOrder> productionOrders, int phaseIndex) {
        for (ProductionOrder productionOrder : productionOrders) {
            Product product = productionOrder.getProduct();
            long wantedQty = 0;

            if (phaseIndex != 2) {
                if (productionOrder.getQty() > warehouse.getMax(product)) wantedQty = warehouse.getMax(product);
                else if (productionOrder.getQty() < warehouse.getMax(product) && productionOrder.getQty() > 0)
                    wantedQty = productionOrder.getQty();
                else if (productionOrder.getQty() <= 0) wantedQty = 0;

            } else {
                wantedQty = warehouse.getMax(product) - warehouse.getQty(product);
            }
            if (product.getResourceByPreference(phaseIndex) instanceof Wheat) {
                double producibleQty = Math.floor(warehouse.getQty(Wheat.INSTANCE) / product.getCostIn(Wheat.INSTANCE));
                if (wantedQty <= producibleQty) {
                    warehouse.subtract((Wheat.INSTANCE), (long) Math.ceil(wantedQty * product.getCostIn(Wheat.INSTANCE)));
                    warehouse.add(product, wantedQty);
                    productionOrder.setQty(wantedQty);
                } else {
                    warehouse.subtract((Wheat.INSTANCE), (long) Math.ceil(producibleQty * product.getCostIn(Wheat.INSTANCE)));
                    warehouse.add(product, (long) producibleQty);
                    productionOrder.setQty((long) producibleQty);
                }
            } else if (product.getResourceByPreference(phaseIndex) instanceof Corn) {
                double producibleQty = Math.floor(warehouse.getQty(Corn.INSTANCE) / product.getCostIn(Corn.INSTANCE)) - Math.floor(warehouse.getQty(Corn.INSTANCE) / product.getCostIn(Corn.INSTANCE)) % 4;
                if (wantedQty % 4 == 0 && wantedQty <= producibleQty) {
                    warehouse.subtract((Corn.INSTANCE), (long) Math.ceil(wantedQty * product.getCostIn(Corn.INSTANCE)));
                    warehouse.add(product, wantedQty);
                    productionOrder.setQty(wantedQty);
                } else if (wantedQty + (4 - wantedQty % 4) <= producibleQty) {
                    warehouse.subtract((Corn.INSTANCE), (long) Math.ceil((wantedQty + (4 - wantedQty % 4)) * product.getCostIn(Corn.INSTANCE)));
                    warehouse.add(product, (wantedQty + (4 - wantedQty % 4)));
                    productionOrder.setQty((wantedQty + (4 - wantedQty % 4)));
                } else {
                    warehouse.subtract((Corn.INSTANCE), (long) Math.ceil(producibleQty * product.getCostIn(Corn.INSTANCE)));
                    warehouse.add(product, (long) producibleQty);
                    productionOrder.setQty((long) producibleQty);
                }
            }
        }
    }

    public void produce(List<ProductionOrder> productionOrders) {
        logger.info("Production has started.");
        for (int phaseIndex = 0; phaseIndex < 3; phaseIndex++) {
            if (phaseIndex == 0)
                logger.info("Phase 1: Producing the wanted quantities from primary resources.");
            else if (phaseIndex == 1)
                logger.info("Phase 2: Producing the wanted quantities from secondary resources.");
            else
                logger.info("Phase 3: Producing over the wanted quantities from primary resources.");
            phase(productionOrders, phaseIndex);
                logger.info("Done!");
        }
    }
}
