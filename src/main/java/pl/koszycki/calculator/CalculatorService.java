package pl.koszycki.calculator;

import pl.koszycki.calculator.exception.InvalidMathExpressionException;
import pl.koszycki.calculator.integral.AsynchronousIntegralParameters;
import pl.koszycki.calculator.integral.AsynchronousIntegralService;
import pl.koszycki.calculator.parser.ReversePolishNotationParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

import javax.enterprise.context.SessionScoped;

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
    if (!CalculatorValidator.isMathExpressionValid(mathExpression)) {
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
      throws InterruptedException {
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
