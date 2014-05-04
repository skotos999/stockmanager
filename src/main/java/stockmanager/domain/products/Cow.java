package stockmanager.domain.products;

import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.Cost;
import stockmanager.domain.stock.Stockable;

import java.util.HashMap;
import java.util.Map;

public enum Cow implements Product {
    INSTANCE {
        @Override
        public Double getCostIn(Resource unit) {
            return null;
        }

        @Override
        public int getResourcePreferenceFor(Resource resource) {
            return 0;
        }

        @Override
        public Resource getResourceByPreference(int preferenceIndex) {
            return null;
        }
    };
    private final int salePrice;
    private final Map<Resource, Cost> costs;
    private final Resource primaryResource = Wheat.INSTANCE;
    private final Resource secondaryResource = Corn.INSTANCE;
    private int priority;

    private Cow() {
        this.salePrice = 45;
        this.costs = new HashMap<Resource, Cost>();
        this.costs.put(Wheat.INSTANCE, new Cost(3, Wheat.INSTANCE));
        this.costs.put(Corn.INSTANCE, new Cost(11.25, Corn.INSTANCE));
    }
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getSalePrice() {
        return salePrice;
    }
}
