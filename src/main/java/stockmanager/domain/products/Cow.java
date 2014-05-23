package stockmanager.domain.products;

import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Cow implements Product {
    INSTANCE;
    private final int salePrice;
    private final Map<Resource, Double> costs;
    private final List<Resource> resourcePreferences;
    private int priority;

    private Cow() {
        this.salePrice = 45;
        this.costs = new HashMap<>();
        this.costs.put(Wheat.INSTANCE, 3.00);
        this.costs.put(Corn.INSTANCE, 11.25);
        this.resourcePreferences = new ArrayList<>();
        resourcePreferences.add(Wheat.INSTANCE);
        resourcePreferences.add(Corn.INSTANCE);
    }

    public Double getCostIn(Resource unit) {
        return costs.get(unit);
    }

    public Resource getResourceByPreference(int preferenceIndex) {
        if (preferenceIndex >= resourcePreferences.size())
            //return resourcePreferences.get(resourcePreferences.size() - 1);
            return resourcePreferences.get(0);
        if (preferenceIndex < 0) return resourcePreferences.get(0);
        return resourcePreferences.get(preferenceIndex);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSalePrice() {
        return salePrice;
    }
}
