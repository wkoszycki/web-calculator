package com.cinemacity.calculator.service;

import com.cinemacity.calculator.TestUtil;
import com.cinemacity.calculator.exception.InvalidMathStringException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class CalculatorServiceTest {

    CalculatorService service;

    @Before
    public void setUp() {
        service = new CalculatorService();
        service.init();
    }

    public CalculatorServiceTest() {
    }

    @Test
    public void testPositiveCases() throws InvalidMathStringException {
        String result = "69";
        for (String positiveCase : TestUtil.positiveCases) {
            assertEquals("Comparision failed for expr:" + positiveCase,
                result, service.calculateString(positiveCase));
        }
    }

    @Test(expected = InvalidMathStringException.class)
    public void testBeginWithLetters() throws InvalidMathStringException {
        service.calculateString("cos1+1");
    }

    @Test(expected = InvalidMathStringException.class)
    public void testLettersInside() throws InvalidMathStringException {
        service.calculateString("12a3-54");
    }

    @Test(expected = InvalidMathStringException.class)
    public void testWhiteSpaces() throws InvalidMathStringException {
        service.calculateString(" 98[*5");
    }

    @Test(expected = InvalidMathStringException.class)
    public void testNewLine() throws InvalidMathStringException {
        service.calculateString("23-4*(50/\\n8)+[43+{5]");
    }
}
