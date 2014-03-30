package products;

import resources.Corn;
import resources.Resource;
import resources.Wheat;
import util.Cost;

import java.util.HashMap;
import java.util.Map;

public enum Pig implements Product{
    INSTANCE;
    private final int salePrice;
    private final Map<Resource, Cost> costs;
    private int priority;

    private Pig() {
        this.salePrice = 25;
        this.costs = new HashMap<Resource, Cost>();
        this.costs.put(Wheat.INSTANCE, new Cost(1.67, Wheat.INSTANCE));
        this.costs.put(Corn.INSTANCE, new Cost(6.25, Corn.INSTANCE));
    }
}
