package com.wkoszycki.calculator;

import java.util.regex.Pattern;

class CalculatorValidator {
//TODO: Improve regex to match only numbers brackets and operators
  private static final Pattern LETTERS_OR_SPACES_PATTERN = Pattern.compile("[a-zA-Z||\\s]");

  static boolean isMathStringValid(String mathString) {
    return mathString != null
           && !containsLettersOrSpaces(mathString);
  }

  private static boolean containsLettersOrSpaces(String mathString) {
    return LETTERS_OR_SPACES_PATTERN.matcher(mathString).find();
  }

}
