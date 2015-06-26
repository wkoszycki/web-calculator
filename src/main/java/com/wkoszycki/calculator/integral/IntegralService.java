package com.wkoszycki.calculator.integral;

public class IntegralService {


  public double calculateEtoX(DefiniteIntegralParameters definiteIntegralParameters) {
    return Math.pow(Math.E, definiteIntegralParameters.endRange) - Math
        .pow(Math.E, definiteIntegralParameters.startRange);
  }
}
