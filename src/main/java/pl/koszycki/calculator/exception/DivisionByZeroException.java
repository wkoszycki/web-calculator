package pl.koszycki.calculator.exception;

public class DivisionByZeroException extends InvalidMathExpressionException {

  public DivisionByZeroException() {
    super("Division by zero is undefined");
  }
}
