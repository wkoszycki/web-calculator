package com.cinemacity.calculator.exception;

/**
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class InvalidMathStringException extends Exception {

  public InvalidMathStringException(String message) {
    super(message);
  }

  public InvalidMathStringException() {
    super("Mathematical string is invalid");
  }

}
