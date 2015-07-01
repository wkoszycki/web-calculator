package com.wkoszycki.calculator.parser;

import com.wkoszycki.calculator.exception.DivisionByZeroException;
import com.wkoszycki.calculator.exception.InvalidMathExpressionException;

import java.util.Stack;

import static com.wkoszycki.calculator.parser.ParserConfiguration.isOperator;

class PostfixEvaluator {

  public Double evaluatePostfix(String[] postfix) {
    Stack<Double> operands = new Stack<>();

    for (String currentChar : postfix) {
      if (isOperator(currentChar)) {
        double secondOperand = operands.pop();
        double firstOperand = operands.pop();
        operands.push(calculate(firstOperand, secondOperand, currentChar));
      } else {
        try {
          operands.push(Double.parseDouble(currentChar));
        } catch (NumberFormatException e) {
          throw new InvalidMathExpressionException("Closing bracket is missing");
        }
      }
    }
    return operands.lastElement();
  }

  private double calculate(double firstOperand, double secondOperand, String operator) {
    switch (operator) {
      case "+":
        return firstOperand + secondOperand;
      case "-":
        return firstOperand - secondOperand;
      case "*":
        return firstOperand * secondOperand;
      case "/":
        if (secondOperand != 0) {
          return firstOperand / secondOperand;
        }
        throw new DivisionByZeroException();
      case "^":
        return Math.pow(firstOperand, secondOperand);
      case "√":
        return Math.sqrt(firstOperand);
      default:
        throw new AssertionError("Invalid Operator");
    }
  }

}
