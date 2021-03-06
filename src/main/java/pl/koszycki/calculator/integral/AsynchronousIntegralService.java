package pl.koszycki.calculator.integral;

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
public class AsynchronousIntegralService {


  public static double getResultFromTasks(List<Future<Double>> futures) {
    double result = 0;
    try {
      for (Future<Double> subIntegralFuture : futures) {
        result += subIntegralFuture.get();
      }
    } catch (InterruptedException | ExecutionException e) {
      throw new IllegalStateException("Error while performing asynchronous tasks");
    }
    return result;
  }

  public List<Future<Double>> calculateEtoX(
      AsynchronousIntegralParameters asynchronousIntegralParameters)
      throws InterruptedException {

    final Set<SubIntegral> subIntegrals = prepareSubIntegrals(asynchronousIntegralParameters);
    int numberOfThreads = defineNumberOfThreads(asynchronousIntegralParameters);
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    return executor.invokeAll(subIntegrals);
  }

  private int defineNumberOfThreads(AsynchronousIntegralParameters asynchronousIntegralParameters) {
    return asynchronousIntegralParameters.numberOfThreads == 0 ? 1
                                                               : asynchronousIntegralParameters.numberOfThreads;
  }

  Set<SubIntegral> prepareSubIntegrals(
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
