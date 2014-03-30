package stockmanager;

import java.math.BigDecimal;

public class Price {
    long amount;
    Resource resource;

    public Price(long amount, Resource resource){
        this.amount = amount;
        this.resource = resource;
    }

}
