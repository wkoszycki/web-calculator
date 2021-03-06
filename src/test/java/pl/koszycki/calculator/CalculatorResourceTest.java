package pl.koszycki.calculator;

import pl.koszycki.calculator.exception.InvalidMathExpressionException;
import pl.koszycki.calculator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CalculatorResourceTest {

  private CalculatorResource instance;

  @Before
  public void setUp() {
    this.instance = new CalculatorResource(new CalculatorService());
  }

  @Test
  public void testPositiveCases() {
    for (String positiveCase : TestUtil.positiveCases) {
      Response response = instance.calculate(positiveCase);
      assertEquals("httpStatus code differs:" + positiveCase,
                   200, response.getStatus());
    }
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testNegativeCases() {
    for (String negativeCase : TestUtil.negativeCases) {
      instance.calculate(negativeCase);
    }
  }

}
