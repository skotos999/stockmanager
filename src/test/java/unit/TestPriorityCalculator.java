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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestPriorityCalculator {

    private Sheriff sheriff;
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
    }

    //calculatePriority_AtLeast2PrioritiesAreEqualAtFirstCalculation_ReturnsDistinctPriorities
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
}
