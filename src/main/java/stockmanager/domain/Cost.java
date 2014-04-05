package stockmanager.domain;

import stockmanager.domain.resources.Resource;

public class Cost {
    Resource unit;
    double amount;

    public Cost(double amount, Resource unit) {
        this.amount = amount; //TODO: amount cannot be below zero
        this.unit = unit;
    }

    public double getAmount() {
        return this.amount;
    }

}
