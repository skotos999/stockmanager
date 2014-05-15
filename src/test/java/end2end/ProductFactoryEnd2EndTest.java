package end2end;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductFactoryEnd2EndTest {

    private ApplicationRunner application;

    @Before
    public void setUp() {
        application = new ApplicationRunner();
    }

    @Test
    public void calculatePrioritiesWhenAllPrioritiesAreDistinctAtFirstCalculation() {
        application.start();
        application.calculate();
        application.showsPriority();

    }

    @Test
    public void test() {

    }


}
