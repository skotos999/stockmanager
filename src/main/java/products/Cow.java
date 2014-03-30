package products;

import resources.Corn;
import resources.Resource;
import resources.Wheat;
import util.Cost;

import java.util.HashMap;
import java.util.Map;

public enum Cow implements Product {
    INSTANCE;
    private final int salePrice;
    private final Map<Resource, Cost> costs;
    private int priority;

    private Cow() {
        this.salePrice = 45;
        this.costs = new HashMap<Resource, Cost>();
        this.costs.put(Wheat.INSTANCE, new Cost(3, Wheat.INSTANCE));
        this.costs.put(Corn.INSTANCE, new Cost(11.25, Corn.INSTANCE));
    }
}
