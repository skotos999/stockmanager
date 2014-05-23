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
    /**
     * Sale price of Cow.
     */
    private final int salePrice;
    /**
     * Production costs of Cow in each {@link stockmanager.domain.resources.Resource}.
     */
    private final Map<Resource, Double> costs;
    /**
     * The order of resource preferences.
     */
    private final List<Resource> resourcePreferences;
    /**
     * Priority of Cow. Defines the order of production.
     */
    private int priority;

    /**
     * Constructs a {@code Cow} object with default values.
     */
    private Cow() {
        this.salePrice = 45;
        this.costs = new HashMap<>();
        this.costs.put(Wheat.INSTANCE, 3.00);
        this.costs.put(Corn.INSTANCE, 11.25);
        this.resourcePreferences = new ArrayList<>();
        resourcePreferences.add(Wheat.INSTANCE);
        resourcePreferences.add(Corn.INSTANCE);
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
