package com.wkoszycki.calculator;

import com.wkoszycki.calculator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CalculatorResourceTest {

  CalculatorResource instance;

  public CalculatorResourceTest() {
  }

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

  @Test
  public void testNegativeCases() {
    for (String negativeCase : TestUtil.negativeCases) {
      Response response = instance.calculate(negativeCase);
      assertEquals("httpStatus code differs:" + negativeCase,
                   400, response.getStatus());
    }
  }

}
