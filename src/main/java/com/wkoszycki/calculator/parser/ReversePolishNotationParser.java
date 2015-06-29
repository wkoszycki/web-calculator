package com.wkoszycki.calculator.parser;

public class ReversePolishNotationParser {

  public Double parseMathExpression(String mathExpression) {
    Double result;
    final String[] postfix = new InfixConverter(mathExpression)
        .convertToPostfix();
    result = new PostfixEvaluator().evaluatePostfix(postfix);
    return result;
  }

}
