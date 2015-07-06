package pl.koszycki.calculator.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ParserConfiguration {

  private static final int DEFAULT_PRIORITY = 0;
  static String OPERATORS_TO_SPLIT;
  private static final Map<String, Integer> OPERATORS_WITH_PRIORITY;
  private static final Set<String> OPEN_BRACKETS;
  private static final Set<String> CLOSED_BRACKETS;

  static {
    OPERATORS_WITH_PRIORITY = new HashMap<>();
    OPEN_BRACKETS = new HashSet<>(Arrays.asList("(", "{", "["));
    CLOSED_BRACKETS = new HashSet<>(Arrays.asList(")", "}", "]"));
    fillOperatorsMap();
    initializeOperatorsToSplit();
  }

  private static void fillOperatorsMap() {
    OPERATORS_WITH_PRIORITY.put("^", 3);
    OPERATORS_WITH_PRIORITY.put("*", 2);
    OPERATORS_WITH_PRIORITY.put("/", 2);
    OPERATORS_WITH_PRIORITY.put("+", 1);
    OPERATORS_WITH_PRIORITY.put("-", 1);
  }

  private static void initializeOperatorsToSplit() {
    Set<String> operators = new HashSet<>(OPEN_BRACKETS);
    StringBuilder sb = new StringBuilder();
    operators.addAll(CLOSED_BRACKETS);
    operators.addAll(OPERATORS_WITH_PRIORITY.keySet());
    for (String operator : operators) {
      sb.append(operator);
    }
    OPERATORS_TO_SPLIT = sb.toString();
  }


  static boolean hasLastElementHigherPriority(String currentElement, String lastElement) {
    return getOperatorPriority(lastElement) >= getOperatorPriority(currentElement);
  }

  private static int getOperatorPriority(String lastElement) {
    return isOperator(lastElement) ? ParserConfiguration.OPERATORS_WITH_PRIORITY.get(lastElement)
                                   : ParserConfiguration.DEFAULT_PRIORITY;
  }

  static boolean isOperator(String token) {
    return ParserConfiguration.OPERATORS_WITH_PRIORITY.containsKey(token);
  }

  static boolean isOpenBracket(String token) {
    return ParserConfiguration.OPEN_BRACKETS.contains(token);
  }

  static boolean isBracket(String token) {
    return ParserConfiguration.OPEN_BRACKETS.contains(token) || ParserConfiguration.CLOSED_BRACKETS
        .contains(token);
  }

}
