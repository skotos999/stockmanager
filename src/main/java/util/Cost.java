package util;

import resources.Resource;

public class Cost {
    Resource unit;
    double amount;

    public Cost(double amount, Resource unit){
        this.amount = amount;
        this.unit = unit;
    }

}
