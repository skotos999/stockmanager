package stockmanager.domain.stock;

import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public enum Warehouse {
    INSTANCE;

    private Map<Stockable, AtomicLong> stockItems;
    private Map<Product, Long> productMax;

    private Warehouse() {
        stockItems = new HashMap<>();
        stockItems.put(Corn.INSTANCE, new AtomicLong(0));
        stockItems.put(Wheat.INSTANCE, new AtomicLong(0));
        stockItems.put(Cow.INSTANCE, new AtomicLong(0));
        stockItems.put(Sheep.INSTANCE, new AtomicLong(0));
        stockItems.put(Pig.INSTANCE, new AtomicLong(0));
        productMax = new HashMap<>();
        productMax.put(Cow.INSTANCE, 50L);
        productMax.put(Sheep.INSTANCE, 75L);
        productMax.put(Pig.INSTANCE, 120L);
    }

    public long getQty(Stockable item) {
        return stockItems.get(item).longValue();
    }

    public long getMax(Product product) {
        return productMax.get(product);
    }

    public long add(Stockable item, long deltaQty) {
        return stockItems.get(item).addAndGet(deltaQty);
    }

    public long subtract(Stockable item, Long deltaQty) {
        long updatedValue = stockItems.get(item).addAndGet(-deltaQty);
        if (updatedValue < 0)
            stockItems.get(item).set(0);
        return stockItems.get(item).longValue();
    }
}
