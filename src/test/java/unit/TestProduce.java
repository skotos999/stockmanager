package unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;
import stockmanager.service.ProductFactory;
import stockmanager.service.ProductionOrder;
import stockmanager.domain.stock.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestProduce {

    private Sheriff sheriff;
    private Warehouse warehouse;
    private List<Product> products;

    @Before
    public void setUp() {
        sheriff = new Sheriff();

        warehouse = Warehouse.INSTANCE;
        warehouse.add(Wheat.INSTANCE, 50L);
        warehouse.add(Corn.INSTANCE, 100L);

        products = new ArrayList<Product>() {
            {
                add(Cow.INSTANCE);
                add(Sheep.INSTANCE);
                add(Pig.INSTANCE);
            }
        };
    }

    @Test
    public void produce_EnoughResourcesAreAvailableToPerformPhaseOneOnly_ProducesTheExactValuesAs2ndParam() {
        List<ProductionOrder> productionOrders = sheriff.createProductionOrders(products, new Long[]{10L, 8L, 16L});
        ProductFactory.INSTANCE.produce(productionOrders);
        assertEquals(10L, warehouse.getQty(Cow.INSTANCE));
        assertEquals(8L, warehouse.getQty(Sheep.INSTANCE));
        assertEquals(16L, warehouse.getQty(Pig.INSTANCE));
    }

    @Test
    public void produce_EnoughResourcesAreAvailableToPerformPhaseOneAndPhaseTwo_DunnoRandomStuff() {
        List<ProductionOrder> productionOrders = sheriff.createProductionOrders(products, new Long[]{10L, 20L, 8L});
        ProductFactory.INSTANCE.produce(productionOrders);
        assertEquals(5L, warehouse.getQty(Cow.INSTANCE));
        assertEquals(20L, warehouse.getQty(Sheep.INSTANCE));
        assertEquals(8L, warehouse.getQty(Pig.INSTANCE));
    }

    @Test
    public void produce_EnoughResourcesAreAvailableToPerformAllPhases_DunnoRandomStuff() {
        List<ProductionOrder> productionOrders = sheriff.createProductionOrders(products, new Long[]{1L, 1L, 1L});
        ProductFactory.INSTANCE.produce(productionOrders);
        assertEquals(15L, warehouse.getQty(Cow.INSTANCE));
        assertEquals(1L, warehouse.getQty(Sheep.INSTANCE));
        assertEquals(16L, warehouse.getQty(Pig.INSTANCE));
    }

    @After
    public void clearUp() {
        warehouse.subtract(Cow.INSTANCE, warehouse.getQty(Cow.INSTANCE));
        warehouse.subtract(Sheep.INSTANCE, warehouse.getQty(Sheep.INSTANCE));
        warehouse.subtract(Pig.INSTANCE, warehouse.getQty(Pig.INSTANCE));
        warehouse.subtract(Wheat.INSTANCE, warehouse.getQty(Wheat.INSTANCE));
        warehouse.subtract(Corn.INSTANCE, warehouse.getQty(Corn.INSTANCE));
    }
}
