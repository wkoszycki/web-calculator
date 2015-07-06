package pl.koszycki.calculator.integral;

import java.util.concurrent.Callable;

class SubIntegral implements Callable<Double> {

  private final long element;

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
