package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathExpressionException;
import com.wkoszycki.calculator.integral.AsynchronousIntegralParameters;
import com.wkoszycki.calculator.integral.AsynchronousIntegralService;
import com.wkoszycki.calculator.parser.ReversePolishNotationParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.enterprise.context.SessionScoped;

import static com.wkoszycki.calculator.CalculatorValidator.isMathExpressionValid;

@SessionScoped
class CalculatorService implements Serializable {

  private final List<String> history;
  private final Map<Long, List<Future<Double>>> calculationOrders;

  CalculatorService() {
    calculationOrders = new HashMap<>();
    history = new ArrayList<>();
  }

  public String calculateMathExpression(String mathExpression) throws
                                                               InvalidMathExpressionException {
    if (!isMathExpressionValid(mathExpression)) {
      throw new InvalidMathExpressionException();
    }
    final ReversePolishNotationParser parser = new ReversePolishNotationParser();
    final Double result = parser.parseMathExpression(mathExpression);
    history.add(mathExpression);
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

  List<String> getHistory() {
    return history;
  }

}
