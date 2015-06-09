package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;
import com.wkoszycki.calculator.parser.InfixConverter;
import com.wkoszycki.calculator.parser.PostfixEvaluator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import static com.wkoszycki.calculator.CalculatorValidator.isMathStringValid;

@SessionScoped
class CalculatorService implements Serializable {

  private final List<String> operations = new ArrayList<>();

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
