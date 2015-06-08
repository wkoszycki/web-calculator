package com.wkoszycki.calculator.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ParserConfiguration {

  private static final int DEFAULT_PRIORITY = 0;
  static String OPERATORS_TO_SPLIT;
  private static final Map<String, Integer> OPERATORS_WITH_PRIORITY;
  private static final Set<String> BRACKETS_REPOSITORY;

  static {
    OPERATORS_WITH_PRIORITY = new HashMap<>();
    BRACKETS_REPOSITORY = new HashSet<>();
    fillOperatorsMap();
    fillBracketsRepository();
    initializeOperatorsToSplit();
  }

  private static void fillBracketsRepository() {
    BRACKETS_REPOSITORY.add("(");
    BRACKETS_REPOSITORY.add(")");
    BRACKETS_REPOSITORY.add("{");
    BRACKETS_REPOSITORY.add("}");
    BRACKETS_REPOSITORY.add("[");
    BRACKETS_REPOSITORY.add("]");
  }

  private static void fillOperatorsMap() {
    OPERATORS_WITH_PRIORITY.put("^", 3);
    OPERATORS_WITH_PRIORITY.put("*", 2);
    OPERATORS_WITH_PRIORITY.put("/", 2);
    OPERATORS_WITH_PRIORITY.put("+", 1);
    OPERATORS_WITH_PRIORITY.put("-", 1);
  }
  private static void initializeOperatorsToSplit() {
    StringBuilder sb = new StringBuilder();
    Set<String> operators = new HashSet<>(BRACKETS_REPOSITORY);
    operators.addAll(OPERATORS_WITH_PRIORITY.keySet());
    for (String operator : operators) {
      sb.append(operator);
    }
    OPERATORS_TO_SPLIT=sb.toString();
  }


  static boolean hasLastElementHigherPriority(String currentElement, String lastElement) {
    return getOperatorPriority(lastElement) >= getOperatorPriority(currentElement);
  }

  private static int getOperatorPriority(String lastElement) {
    return isOperator(lastElement) ? ParserConfiguration.OPERATORS_WITH_PRIORITY.get(lastElement)
                                   : ParserConfiguration.DEFAULT_PRIORITY;
  }

  static boolean isOperator(String character) {
    return ParserConfiguration.OPERATORS_WITH_PRIORITY.containsKey(character);
  }

  static boolean isOpenBracket(String currentChar) {
    return "(".equals(currentChar);
  }

  static boolean isBracket(String c) {
    return ParserConfiguration.BRACKETS_REPOSITORY.contains(c);
  }

}
