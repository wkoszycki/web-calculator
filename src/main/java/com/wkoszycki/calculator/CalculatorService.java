package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;
import com.wkoszycki.calculator.integral.AsynchronousIntegralParameters;
import com.wkoszycki.calculator.integral.AsynchronousIntegralService;
import com.wkoszycki.calculator.parser.InfixConverter;
import com.wkoszycki.calculator.parser.PostfixEvaluator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.enterprise.context.SessionScoped;

import static com.wkoszycki.calculator.CalculatorValidator.isMathStringValid;

@SessionScoped
class CalculatorService implements Serializable {

  private final List<String> operations;
  private final Map<Long, List<Future<Double>>> calculationOrders;

  CalculatorService() {
    calculationOrders = new HashMap<>();
    operations = new ArrayList<>();
  }

  public String calculateString(String mathString) throws InvalidMathStringException {
    if (!isMathStringValid(mathString)) {
      throw new InvalidMathStringException();
    }
    final String[] postfix = new InfixConverter(unifyBrackets(mathString))
        .convertToPostfix();
    final Double result = new PostfixEvaluator().evaluatePostfix(postfix);
    operations.add(mathString);
    if (isInteger(result)) {
      return String.valueOf(result.intValue());
    }
    return result.toString();
  }

  public Long calculateEtoX(AsynchronousIntegralParameters parameters)
      throws ExecutionException, InterruptedException {
    final List<Future<Double>>
        futures =
        new AsynchronousIntegralService().calculateEtoX(parameters);
    final Long operationId = generateRandomKey();
    calculationOrders.put(operationId, futures);
    return operationId;
  }

  public double getCalculationOrderResult(Long id) {
    if (id == null || !calculationOrders.containsKey(id)) {
      throw new IllegalArgumentException("Invalid order id");
    }
    return AsynchronousIntegralService.getResultFromTasks(calculationOrders.get(id));
  }

  private Long generateRandomKey() {
    return new Random().nextLong();
  }

  private boolean isInteger(Double input) {
    return input == Math.floor(input);
  }

  private String unifyBrackets(String mathString) {
    //TODO: Change it to Pattern Matcher to be more efficient.
    return mathString
        .replaceAll("\\{", "(")
        .replaceAll("\\}", ")")
        .replaceAll("\\[", "(")
        .replaceAll("\\]", ")");
  }

  public List<String> getOperations() {
    return operations;
  }

}
