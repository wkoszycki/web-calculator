package com.wkoszycki.calculator.integral;

import java.util.concurrent.Callable;

public class SubIntegral implements Callable<Double> {

  long element;

  public SubIntegral(long element) {
    this.element = element;
  }

  @Override
  public Double call() throws Exception {
    return power(element + 1) - power(element);
  }


  private double power(long element) {
    return Math.pow(Math.E, element);
  }

}
