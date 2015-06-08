package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;
import com.wkoszycki.calculator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

  CalculatorService service;

  @Before
  public void setUp() {
    service = new CalculatorService();
  }

  @Test
  public void testPositiveCases() throws InvalidMathStringException {
    String result = "69";
    for (String positiveCase : TestUtil.positiveCases) {
      assertEquals("Comparision failed for expr:" + positiveCase,
                   result, service.calculateString(positiveCase));
    }
  }

  @Test
  public void testDouble() throws InvalidMathStringException {
    assertEquals("2.4", service.calculateString("12/5"));
  }

  @Test(expected = InvalidMathStringException.class)
  public void testBeginWithLetters() throws InvalidMathStringException {
    service.calculateString("cos1+1");
  }

  @Test(expected = InvalidMathStringException.class)
  public void testLettersInside() throws InvalidMathStringException {
    service.calculateString("12a3-54");
  }

  @Test(expected = InvalidMathStringException.class)
  public void testWhiteSpaces() throws InvalidMathStringException {
    service.calculateString(" 98[*5");
  }

  @Test(expected = InvalidMathStringException.class)
  public void testNewLine() throws InvalidMathStringException {
    service.calculateString("23-4*(50/\\n8)+[43+{5]");
  }

  @Test(expected = InvalidMathStringException.class)
  public void testDivisionByZero() throws InvalidMathStringException {
    service.calculateString("5/0");
  }

  @Test(expected = InvalidMathStringException.class)
  public void testNestedDivisionByZero() throws InvalidMathStringException {
    service.calculateString("5/(2-2)");
  }
}
