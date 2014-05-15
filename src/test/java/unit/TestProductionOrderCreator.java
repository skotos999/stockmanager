package unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.service.ProductionOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionOrderCreator {
    private Sheriff sheriff;
    private List<Product> products;


    @Before
    public void setUp() {
        sheriff = new Sheriff();
        products = new ArrayList<Product>() {
            {
                add(Cow.INSTANCE);
                add(Sheep.INSTANCE);
                add(Pig.INSTANCE);
            }
        };
    }

    @Test
    public void createProductionOrders6() {
        sheriff.calculateProductPriority(products, new Long[]{1L, 2L, 3L});
        assertEquals(3, Cow.INSTANCE.getPriority());
        assertEquals(2, Sheep.INSTANCE.getPriority());
        assertEquals(1, Pig.INSTANCE.getPriority());

        List<ProductionOrder> productionOrders = sheriff.createProductionOrders(products, new Long[]{1L, 2L, 3L});
        assertEquals(Pig.INSTANCE, productionOrders.get(0).getProduct());
        assertEquals(3L, productionOrders.get(0).getQty());
        assertEquals(Sheep.INSTANCE, productionOrders.get(1).getProduct());
        assertEquals(2L, productionOrders.get(1).getQty());
        assertEquals(Cow.INSTANCE, productionOrders.get(2).getProduct());
        assertEquals(1L, productionOrders.get(2).getQty());
    }
}
