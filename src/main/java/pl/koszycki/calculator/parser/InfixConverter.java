package pl.koszycki.calculator.parser;

import pl.koszycki.calculator.exception.InvalidMathExpressionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


class InfixConverter {

  private final String mathString;
  private final Stack<String> operators;
  private final List<String> output = new ArrayList<>();


  InfixConverter(String mathString) {
    this.mathString = mathString;
    this.operators = new Stack<>();
  }

  String[] convertToPostfix() {
    StringTokenizer
        tokenizer =
        new StringTokenizer(mathString, ParserConfiguration.OPERATORS_TO_SPLIT, true);
    while (tokenizer.hasMoreTokens()) {
      String currentToken = tokenizer.nextToken();
      if (ParserConfiguration.isOperator(currentToken)) {
        if (!operators.isEmpty() && ParserConfiguration.hasLastElementHigherPriority(currentToken,
                                                                                     operators
                                                                                         .lastElement())) {
          moveOperatorsToOutput();
        }
        operators.push(currentToken);
      } else if (ParserConfiguration.isBracket(currentToken)) {
        if (ParserConfiguration.isOpenBracket(currentToken)) {
          operators.push(currentToken);
        } else {
          popOperatorsFromStackTillOpenBracket();
        }
      } else {
        addElementToOutput(currentToken);
      }
    }
    moveOperatorsToOutput();
    return output.toArray(new String[output.size()]);
  }

  private void popOperatorsFromStackTillOpenBracket() {

    while (!ParserConfiguration.isOpenBracket(operators.peek())) {
      addElementToOutput(operators.pop());
      if (operators.isEmpty()) {
        throw new InvalidMathExpressionException("Opening bracket is missing");
      }
    }
    operators.pop();
  }


  private void moveOperatorsToOutput() {
    while (!operators.isEmpty()) {
      addElementToOutput(operators.pop());
    }
  }

  private void addElementToOutput(String aChar) {
    output.add(aChar);
  }

}
