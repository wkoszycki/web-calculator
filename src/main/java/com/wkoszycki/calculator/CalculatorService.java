package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@SessionScoped
public class CalculatorService implements Serializable {

  List<String> operations = new ArrayList<>();

  /**
   * Calculates value from given math string. Throwing exception is expensive, it could be optimized
   * by simply return String value but: optimize only if needed.
   *
   * @param mathString mathematical string
   * @return calculated value
   * @throws com.wkoszycki.calculator.exception.InvalidMathStringException when mathematical
   *                                                                       expression is invalid
   */
  public String calculateString(String mathString) throws InvalidMathStringException {
    if (!CalculatorValidator.isMathStringValid(mathString)) {
      throw new InvalidMathStringException();
    }
    try {
      //TODO: change it to parser &
      ScriptEngineManager mgr = new ScriptEngineManager();
      ScriptEngine engine = mgr.getEngineByName("JavaScript");
      Double result = (Double) engine.eval(unifyBrackets(mathString));
      operations.add(mathString);
      if (isInteger(result)) {
        return String.valueOf(result.intValue());
      }
      return result.toString();
    } catch (ScriptException ex) {
      throw new InvalidMathStringException();
    }
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
