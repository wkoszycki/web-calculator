package com.wkoszycki.calculator.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostfixEvaluatorTest {

  private static final double DELTA = 1e-15;

  @Test
  public void testAllOperationPostfix() {
    final String[] input = {"4", "56", "+", "10", "/", "112", "2", "-", "10", "/", "+"};
    final Double result = new PostfixEvaluator().evaluatePostfix(input);
    assertEquals(17.0, result, DELTA);
  }

}
