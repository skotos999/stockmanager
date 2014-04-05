package end2end;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;
import org.mockito.runners.MockitoJUnitRunner;
import stockmanager.service.DemandApproximator;

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

    }

    @Test
    public void test(){

    }





}
