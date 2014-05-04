package stockmanager.domain.stock;

import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public enum Warehouse {
    INSTANCE;

    private Map<Stockable, AtomicLong> stockItems;

    private Warehouse() {
        stockItems = new HashMap<Stockable, AtomicLong>();
        stockItems.put(Corn.INSTANCE, new AtomicLong(0));
        stockItems.put(Wheat.INSTANCE, new AtomicLong(0));
        stockItems.put(Cow.INSTANCE, new AtomicLong(0));
        stockItems.put(Sheep.INSTANCE, new AtomicLong(0));
        stockItems.put(Pig.INSTANCE, new AtomicLong(0));
    }

    public long getQty(Stockable item) {
        return stockItems.get(item).longValue();
    }

    public long add(Stockable item, long deltaQty){
        return stockItems.get(item).addAndGet(deltaQty);
    }

    public long subtract(Stockable item, Long deltaQty) {
        long updatedValue = stockItems.get(item).addAndGet(-deltaQty);
        if (updatedValue < 0)
            stockItems.get(item).set(0);
        return stockItems.get(item).longValue();
    }
}
