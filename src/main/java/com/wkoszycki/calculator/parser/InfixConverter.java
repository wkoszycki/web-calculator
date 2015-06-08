package com.wkoszycki.calculator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import static com.wkoszycki.calculator.parser.ParserConfiguration.hasLastElementHigherPriority;
import static com.wkoszycki.calculator.parser.ParserConfiguration.isBracket;
import static com.wkoszycki.calculator.parser.ParserConfiguration.isOpenBracket;
import static com.wkoszycki.calculator.parser.ParserConfiguration.isOperator;


public class InfixConverter {

  final String mathString;
  final Stack<String> operators;
  final List<String> output = new ArrayList<>();


  public InfixConverter(String mathString) {
    this.mathString = mathString;
    this.operators = new Stack<>();
  }

  public String[] convertToPostfix() {
    StringTokenizer tokenizer = new StringTokenizer(mathString, ParserConfiguration.OPERATORS_TO_SPLIT, true);
    while (tokenizer.hasMoreTokens()){
      String currentToken =tokenizer.nextToken();
      if (isOperator(currentToken)) {
        if (!operators.isEmpty() && hasLastElementHigherPriority(currentToken,
                                                                 operators.lastElement())) {
          moveOperatorsToOutput();
        }
        operators.push(currentToken);
      } else if (isBracket(currentToken)) {
        if (isOpenBracket(currentToken)) {
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
    while (!isOpenBracket(operators.peek())) {
      addElementToOutput(operators.pop());
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
