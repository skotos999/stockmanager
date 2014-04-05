package stockmanager.domain.products;

import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Sheep implements Product {
    INSTANCE;
    private final int salePrice;
    private final Map<Resource, Cost> costs;
    private final List<Resource> resourcePreferences;
    private int priority;

    private Sheep() {
        this.salePrice = 35;
        this.costs = new HashMap<Resource, Cost>();
        this.resourcePreferences = new ArrayList<Resource>();
        resourcePreferences.add(Wheat.INSTANCE);
        resourcePreferences.add(Corn.INSTANCE);
        this.costs.put(Wheat.INSTANCE, new Cost(2.33, Wheat.INSTANCE));
        this.costs.put(Corn.INSTANCE, new Cost(8.75, Corn.INSTANCE));
    }

    public Cost getCostIn(Resource unit) {
        return costs.get(unit);
    }

    public int getResourcePreferenceFor(Resource resource) {
        return resourcePreferences.indexOf(resource);
    }

    public Resource getResourcebyPreference(int preferenceIndex) {
        if (preferenceIndex >= resourcePreferences.size()) return resourcePreferences.get(resourcePreferences.size() - 1);
        if (preferenceIndex < 0) return resourcePreferences.get(0);
        return resourcePreferences.get(preferenceIndex);
    }
}
