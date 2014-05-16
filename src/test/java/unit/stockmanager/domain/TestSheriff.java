package unit.stockmanager.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.Warehouse;
import stockmanager.service.ProductFactory;
import stockmanager.service.ProductionOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSheriff {

    private Sheriff sheriff;
    private Warehouse warehouse = Warehouse.INSTANCE;
    private List<Product> products = new ArrayList<Product>() {
        {
            add(Cow.INSTANCE);
            add(Sheep.INSTANCE);
            add(Pig.INSTANCE);
        }
    };

    @Before
    public void setUp() {
        sheriff = new Sheriff();
        warehouse.add(Wheat.INSTANCE, 50L);
        warehouse.add(Corn.INSTANCE, 100L);
    }

    //naming convention: http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html

    @Test
    public void calculatePriority_AllPrioritiesAreDistinct_ReturnsDistinctPriorities() {
        sheriff.calculateProductPriority(products, new Long[]{24L, 39L, 52L});
        assertEquals(3, Cow.INSTANCE.getPriority());
        assertEquals(1, Sheep.INSTANCE.getPriority());
        assertEquals(2, Pig.INSTANCE.getPriority());
    }

    @Test
    public void calculatePriority_AtLeast2PrioritiesAreEqualAtFirstCalculation_ReturnsDistinctPriorities() {
        sheriff.calculateProductPriority(products, new Long[]{7L, 9L, 52L});
        assertEquals(1, Cow.INSTANCE.getPriority());
        assertEquals(2, Sheep.INSTANCE.getPriority());
        assertEquals(3, Pig.INSTANCE.getPriority());
    }

    @Test
    public void calculatePriority_AtLeast2DemandsEqualZeroAtFirstCalculation_ReturnsDistinctPriorities() {
        sheriff.calculateProductPriority(products, new Long[]{0L, 0L, 52L});
        assertEquals(1, Cow.INSTANCE.getPriority());
        assertEquals(2, Sheep.INSTANCE.getPriority());
        assertEquals(3, Pig.INSTANCE.getPriority());
    }

    @Test
    public void createProductionOrders() {
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
