package com.wkoszycki.calculator.integral;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
public class AsynchronousIntegralService implements IntegralService {

  @Override
  public double calculateEtoX(DefiniteIntegralParameters definiteIntegralParameters) {
    double result = 0;
    try {
      final List<Future<Double>> futureResults
          = calculateEtoX((AsynchronousIntegralParameters) definiteIntegralParameters);
      for (Future<Double> subIntegralFuture : futureResults) {
        result += subIntegralFuture.get();
      }
    } catch (InterruptedException | ExecutionException e) {
      throw new IllegalStateException("Error while performing asynchronous tasks");
    }
    return result;
  }

  public List<Future<Double>> calculateEtoX(
      AsynchronousIntegralParameters asynchronousIntegralParameters)
      throws InterruptedException, ExecutionException {

    final Set<SubIntegral> subIntegrals = prepareSubIntegrals(asynchronousIntegralParameters);

    int numberOfThreads = defineNumberOfThreads(asynchronousIntegralParameters);
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    return executor.invokeAll(subIntegrals);
  }

  private int defineNumberOfThreads(AsynchronousIntegralParameters asynchronousIntegralParameters) {
    return asynchronousIntegralParameters.numberOfThreads == 0 ? 1
                                                               : asynchronousIntegralParameters.numberOfThreads;
  }

  public Set<SubIntegral> prepareSubIntegrals(
      DefiniteIntegralParameters definiteIntegralParameters) {
    HashSet<SubIntegral> subIntegrals = new HashSet<>();
    log.log(Level.INFO, "Splitting integral from {0} to {1} for sub-tasks",
            new Object[]{definiteIntegralParameters.startRange,
                         definiteIntegralParameters.endRange});
    for (long i = definiteIntegralParameters.startRange;
         i < definiteIntegralParameters.endRange; i++) {
      subIntegrals.add(new SubIntegral(i));
    }
    return subIntegrals;
  }
}
