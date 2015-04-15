package com.cinemacity.calculator.rest;

import com.cinemacity.calculator.TestUtil;
import com.cinemacity.calculator.service.CalculatorService;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class CalculatorResourceTest {

  CalculatorResource instance;

  public CalculatorResourceTest() {
  }

  @Before
  public void setUp() {
    this.instance = new CalculatorResource();
    CalculatorService calculatorService = new CalculatorService();
    calculatorService.init();
    instance.setCalculatorService(calculatorService);
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
