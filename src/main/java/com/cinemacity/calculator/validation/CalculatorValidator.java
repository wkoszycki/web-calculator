package com.cinemacity.calculator.validation;

import java.util.regex.Pattern;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
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
        return !REGEX_PATTERN.matcher(mathString).find();
    }

}
