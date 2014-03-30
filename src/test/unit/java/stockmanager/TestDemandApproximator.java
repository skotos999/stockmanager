package stockmanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

import org.junit.runner.RunWith;
import org.mockito.Matchers;
import static org.hamcrest.core.IsEqual.equalTo;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class TestDemandApproximator {

    private DemandApproximator demandApproximator;
 mn
    @Before
    public void setUp() {
        demandApproximator = new DemandApproximator();
    }

    //naming convention: http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
    @Test
    public void approxDemand_5LengthDemandHistory_Calculated() {
        assertThat("", demandApproximator.approximate(new int[]{16, 25, 22, 19, 24}), equalTo(21.2));
    }

    @Test
    public void test(){
        Cow cow = new Cow(new Price(3, new Wheat()));

    }





}
