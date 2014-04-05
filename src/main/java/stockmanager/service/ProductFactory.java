package stockmanager.service;

import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.StockItem;
import stockmanager.domain.stock.Stockable;

import java.util.HashMap;
import java.util.Map;

public enum ProductFactory {
    INSTANCE;

    private Map<Stockable, StockItem> resourceStock;

    private ProductFactory() {
        resourceStock = new HashMap<Stockable, StockItem>();
        resourceStock.put(Wheat.INSTANCE, new StockItem(Wheat.INSTANCE, 50));
        resourceStock.put(Corn.INSTANCE, new StockItem(Corn.INSTANCE, 100));
    }


    public void fillBuffer() {
        long toBeProduced = 17;
        long wheatInStock = resourceStock.get(Wheat.INSTANCE).getQty();
        double costInResource = toBeProduced * Sheep.INSTANCE.getCostIn(Sheep.INSTANCE.getResourcebyPreference(0)).getAmount();
        if (costInResource <= wheatInStock)
            resourceStock.get(Wheat.INSTANCE).subtract((long) Math.ceil(costInResource));
        else resourceStock.get(Wheat.INSTANCE).subtract(calculateFeasibleAmount(wheatInStock, Sheep.INSTANCE, Wheat.INSTANCE));

    }

    private long calculateFeasibleAmount(long inStockQty, Product producibleProduct, Resource fromWhat){
        return (long)Math.floor(inStockQty / producibleProduct.getCostIn(fromWhat).getAmount());
    }
}
