package com.wkoszycki.calculator.integral;

import junit.framework.TestCase;

import org.junit.Test;

public class IntegralServiceTest extends TestCase {

  private static final double DELTA = 1e-15;
  private DefiniteIntegralParameters parameters;


  @Override
  public void setUp() throws Exception {
    parameters = new DefiniteIntegralParameters();
  }

  @Test
  public void testCalculateSimpleIndefinite() throws Exception {
    parameters.startRange = 0;
    parameters.endRange = 4;
    final double integral = new IntegralService().calculateEtoX(parameters);
    assertEquals(53.59815003314423, integral, DELTA);
  }

  //  @Test
  public void testCalculateSimpleIndefinite2() throws Exception {
    parameters.startRange = 2;
    parameters.endRange = 6;
    final double integral = new IntegralService().calculateEtoX(parameters);
    assertEquals(396.03973739380433, integral, DELTA);
  }
}