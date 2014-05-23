package stockmanager.domain;

import stockmanager.db.JDBCHistoryDAO;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.service.ProductFactory;
import stockmanager.service.ProductionOrder;

import java.util.*;

public class Sheriff {

    public void calculateProductPriority(List<Product> products, Long[] quantities) {
        List<Long> priorities = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (quantities[i] < 0L)
                quantities[i] = 0L;
            priorities.add(quantities[i] * products.get(i).getSalePrice());
        }
        if (new HashSet<>(priorities).size() != 3) {
            for (int i = 1; i <= 3; i++) {
                products.get(i - 1).setPriority(i);
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                products.get(priorities.indexOf(Collections.max(priorities))).setPriority(i);
                priorities.set(priorities.indexOf(Collections.max(priorities)), -1L);
            }
        }
    }

    public List<ProductionOrder> createProductionOrders(List<Product> products, Long[] quantities) {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (quantities[i] < 0L)
                quantities[i] = 0L;
        }

        calculateProductPriority(products, quantities);

        int i = 0;
        for (Product product : products) {
            productionOrders.add(new ProductionOrder(product, quantities[i++]));
        }

        Collections.sort(productionOrders, new Comparator<ProductionOrder>() {
            @Override
            public int compare(ProductionOrder o1, ProductionOrder o2) {
                return o1.getProduct().getPriority() - o2.getProduct().getPriority();
            }
        });
        return productionOrders;
    }

    public void startProduction() {
        List<Product> products = new ArrayList<Product>() {
            {
                add(Cow.INSTANCE);
                add(Sheep.INSTANCE);
                add(Pig.INSTANCE);
            }
        };
        ProductFactory.INSTANCE.produce(createProductionOrders(products, JDBCHistoryDAO.INSTANCE.calculateSoldValues()));

    }
}
