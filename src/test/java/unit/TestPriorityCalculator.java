package unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.service.PriorityCalculator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestPriorityCalculator {

    private PriorityCalculator priorityCalculator;
    private Product[] products = new Product[]{Cow.INSTANCE, Sheep.INSTANCE, Pig.INSTANCE};

    @Before
    public void setUp() {
        priorityCalculator = new PriorityCalculator();
    }

    //calcPrior_At least two priorities are equal at first calculation_DistinctPriors
    //calculatePriority_AtLeast2PrioritiesAreEqualAtFirstCalculation_ReturnsDistinctPriorities

    //naming convention: http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
    @Test
    public void calculatePriority_AllPrioritiesAreDistinct_ReturnsDistinctPriorities() {
        priorityCalculator.calculate(products, new Integer[]{24, 39, 52});
        assertEquals(Cow.INSTANCE.getPriority(),3);
        assertEquals(Sheep.INSTANCE.getPriority(),1);
        assertEquals(Pig.INSTANCE.getPriority(),2);
    }

    @Test
    public void calculatePriority_AtLeast2PrioritiesAreEqualAtFirstCalculation_ReturnsDistinctPriorities() {
        priorityCalculator.calculate(products, new Integer[]{7, 9, 52});
        assertEquals(Cow.INSTANCE.getPriority(),1);
        assertEquals(Sheep.INSTANCE.getPriority(),2);
        assertEquals(Pig.INSTANCE.getPriority(),3);
    }

    @Test
    public void calculatePriority_AtLeast2ApproximatedDemandsEqualZeroAtFirstCalculation_ReturnsDistinctPriorities() {
        priorityCalculator.calculate(products, new Integer[]{0, 0, 52});
        assertEquals(Cow.INSTANCE.getPriority(),1);
        assertEquals(Sheep.INSTANCE.getPriority(),2);
        assertEquals(Pig.INSTANCE.getPriority(),3);
    }

}
