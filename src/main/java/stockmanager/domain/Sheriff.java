package stockmanager.domain;

import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.service.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class Sheriff {


    //TODO: do the real calculation
    private List<Product> calculateProductPriority() {
        List<Product> prioritizedProducts = new ArrayList<Product>();
        prioritizedProducts.add(Cow.INSTANCE);
        prioritizedProducts.add(Sheep.INSTANCE);
        prioritizedProducts.add(Pig.INSTANCE);
        return prioritizedProducts;
    }

    private List<ProductionOrder> createProductionOrders(List<Product> prioritizedProducts, long[] quantitites) {
        List<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>();
        int i = 0;
        for (Product product : calculateProductPriority()) {
            productionOrders.add(new ProductionOrder(product, quantitites[i++]));
        }
        return productionOrders;
    }

    public void startProduction() {

        //TODO setUpStock
        ProductFactory.INSTANCE.produce(createProductionOrders(calculateProductPriority(), new long[]{10, 20, 30}));//
    }
}
