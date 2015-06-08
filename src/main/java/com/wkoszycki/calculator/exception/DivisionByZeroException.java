package com.wkoszycki.calculator.exception;

public class DivisionByZeroException extends InvalidMathStringException {

  public DivisionByZeroException() {
    super("Division by zero is undefined");
  }
}
