package com.cinemacity.calculator.validation;

import java.util.regex.Pattern;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class CalculatorValidator {

    private static final Pattern REGEX_PATTERN = Pattern.compile("[a-zA-Z||\\s]");

    /**
     * Validates math string
     *
     * @param mathString mathematical expression
     * @return true if string is valid false otherwise
     */
    public static boolean isMathStringValid(String mathString) {
        if (mathString == null) {
            return false;
        }
        return !REGEX_PATTERN.matcher(mathString).find();
    }

}
