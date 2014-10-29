package com.cinemacity.calculator.validator;

import com.cinemacity.calculator.TestUtil;
import com.cinemacity.calculator.validation.CalculatorValidator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class CalculatorValidatorTest {

    public CalculatorValidatorTest() {
    }

    @Test
    public void testPositiveCase() {
        for (String positiveCase : TestUtil.positiveCases) {
            assertTrue("Failed for expression:" + positiveCase, CalculatorValidator.isMathStringValid(positiveCase));
        }
    }

    @Test
    public void testNegativeCase() {
        for (String negativeCase : TestUtil.negativeCases) {
            assertFalse("Failed for expression:" + negativeCase, CalculatorValidator.isMathStringValid(negativeCase));
        }
    }

}
