package pl.koszycki.calculator.integral;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsynchronousIntegralServiceTest {

  private static final double DELTA = 1e-14;
  private AsynchronousIntegralParameters parameters;
  private AsynchronousIntegralService asynchronousIntegralService;


  @Before
  public void setUp() throws Exception {
    parameters = new AsynchronousIntegralParameters();
    asynchronousIntegralService = new AsynchronousIntegralService();
  }

  @Test
  public void testCalculateSimpleIndefiniteFromZero() throws Exception {
    parameters.startRange = 0;
    parameters.endRange = 4;
    final double integral = AsynchronousIntegralService.getResultFromTasks(
        asynchronousIntegralService.calculateEtoX(parameters));
    Assert.assertEquals(53.59815003314422, integral, DELTA);
  }

  @Test
  public void testCalculateSimpleIndefinite2() throws Exception {
    parameters.startRange = 2;
    parameters.endRange = 6;
    final double integral = AsynchronousIntegralService.getResultFromTasks(
        asynchronousIntegralService.calculateEtoX(parameters));
    Assert.assertEquals(396.03973739380433, integral, DELTA);
  }
}