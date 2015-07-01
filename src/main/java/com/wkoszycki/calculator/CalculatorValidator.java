package com.wkoszycki.calculator;

import java.util.regex.Pattern;

class CalculatorValidator {


  private static final Pattern
      NUMBERS_BRACKETS_OPERATORS_DOT =
      Pattern.compile("^[0-9+\\-*/()\\{}\\[\\]\\.]*$");

  static boolean isMathExpressionValid(String mathString) {
    return mathString != null
           && hasOnlyNumbersBracketsOperatorsDots(mathString);
  }

  private static boolean hasOnlyNumbersBracketsOperatorsDots(String mathString) {
    return NUMBERS_BRACKETS_OPERATORS_DOT.matcher(mathString).matches();
  }

}
