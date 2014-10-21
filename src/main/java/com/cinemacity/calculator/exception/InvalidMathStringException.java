package com.cinemacity.calculator.exception;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class InvalidMathStringException extends Exception {

    public InvalidMathStringException(String message) {
        super(message);
    }

    public InvalidMathStringException() {
        super("Mathematical string is invalid");
    }

}
