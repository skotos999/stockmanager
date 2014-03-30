package products;

import resources.Corn;
import resources.Resource;
import resources.Wheat;
import util.Cost;

import java.util.HashMap;
import java.util.Map;

public enum Sheep implements Product{
    INSTANCE;
    private final int salePrice;
    private final Map<Resource, Cost> costs;
    private int priority;

    private Sheep() {
        this.salePrice = 35;
        this.costs = new HashMap<Resource, Cost>();
        this.costs.put(Wheat.INSTANCE, new Cost(2.33, Wheat.INSTANCE));
        this.costs.put(Corn.INSTANCE, new Cost(8.75, Corn.INSTANCE));
    }
}
