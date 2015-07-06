package pl.koszycki.calculator;

import pl.koszycki.calculator.exception.InvalidMathExpressionException;
import pl.koszycki.calculator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

  private CalculatorService service;

  @Before
  public void setUp() {
    service = new CalculatorService();
  }

  @Test
  public void testPositiveCases() throws InvalidMathExpressionException {
    String result = "69";
    for (String positiveCase : TestUtil.positiveCases) {
      assertEquals("Comparison failed for expr:" + positiveCase,
                   result, service.calculateMathExpression(positiveCase));
    }
  }

  @Test
  public void testDouble() throws InvalidMathExpressionException {
    assertEquals("2.4", service.calculateMathExpression("12/5"));
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testBeginWithLetters() throws InvalidMathExpressionException {
    service.calculateMathExpression("cos1+1");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testLettersInside() throws InvalidMathExpressionException {
    service.calculateMathExpression("12a3-54");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testWhiteSpaces() throws InvalidMathExpressionException {
    service.calculateMathExpression(" 98[*5");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testNewLine() throws InvalidMathExpressionException {
    service.calculateMathExpression("23-4*(50/\\n8)+[43+{5]");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testDivisionByZero() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/0");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testNestedDivisionByZero() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/(2-2)");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testMissingOpeningBracket() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/2-3)");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testMissingClosingBracket() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/(2-3");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testMissingOpeningSquareBracket() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/2-3]");
  }

  @Test(expected = InvalidMathExpressionException.class)
  public void testMissingClosingSquareBracket() throws InvalidMathExpressionException {
    service.calculateMathExpression("5/{[2-3");
  }
}
