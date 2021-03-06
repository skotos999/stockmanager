package stockmanager.domain.products;

import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Resource;
import stockmanager.domain.resources.Wheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Pig implements Product {
    INSTANCE;

    /**
     * Sale price of Pig.
     */
    private final int salePrice;
    /**
     * Production costs of Pig in each {@link stockmanager.domain.resources.Resource}.
     */
    private final Map<Resource, Double> costs;
    /**
     * The order of resource preferences.
     */
    private final List<Resource> resourcePreferences;
    /**
     * Priority of Pig. Defines the order of production.
     */
    private int priority;

    /**
     * Constructs a {@code Pig} object with default values.
     */
    private Pig() {
        this.salePrice = 25;
        this.costs = new HashMap<>();
        this.costs.put(Wheat.INSTANCE, 1.67);
        this.costs.put(Corn.INSTANCE, 6.25);
        this.resourcePreferences = new ArrayList<>();
        resourcePreferences.add(Corn.INSTANCE);
        resourcePreferences.add(Wheat.INSTANCE);
    }

    /**
     * {@inheritDoc}
     */
    public Double getCostIn(Resource unit) {
        return costs.get(unit);
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResourceByPreference(int preferenceIndex) {
        if (preferenceIndex >= resourcePreferences.size())
            //return resourcePreferences.get(resourcePreferences.size() - 1);
            return resourcePreferences.get(0);
        if (preferenceIndex < 0) return resourcePreferences.get(0);
        return resourcePreferences.get(preferenceIndex);
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * {@inheritDoc}
     */
    public int getSalePrice() {
        return salePrice;
    }
}
