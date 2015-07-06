package pl.koszycki.calculator;

import pl.koszycki.calculator.util.TestUtil;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CalculatorValidatorTest {

  @Test
  public void testPositiveCase() {
    for (String positiveCase : TestUtil.positiveCases) {
      assertTrue("Failed for expression:" + positiveCase,
                 CalculatorValidator.isMathExpressionValid(positiveCase));
    }
  }

  @Test
  public void testNegativeCase() {
    for (String negativeCase : TestUtil.negativeCases) {
      assertFalse("Failed for expression:" + negativeCase,
                  CalculatorValidator.isMathExpressionValid(negativeCase));
    }
  }

}
