package pl.koszycki.calculator.parser;

import org.junit.Assert;
import org.junit.Test;

public class InfixConverterTest {

  @Test
  public void testAllOperatorsWithNestedBrackets() {
    final String[] postfix = new InfixConverter("(4+56)/10+((112-2)/10)").convertToPostfix();
    final String[] expected = {"4", "56", "+", "10", "/", "112", "2", "-", "10", "/", "+"};
    Assert.assertArrayEquals(expected,postfix);
  }


}
