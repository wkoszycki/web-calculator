package com.cinemacity.calculator.service;

import com.cinemacity.calculator.TestUtil;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class CalculatorServiceTest {

    CalculatorService service;

    public CalculatorServiceTest() {
        service = new CalculatorService();
        service.init();
    }

    @Test
    public void testPositiveCases() {
        String result = "69";
        for (String positiveCase : TestUtil.positiveCases) {
            assertEquals("Comparision failed for expr:" + positiveCase,
                result, service.calculateString(positiveCase));
        }
    }

    @Test
    public void testNegativeCases() {
        for (String negativeCase : TestUtil.negativeCases) {
            assertEquals("Comparision failed for expr:" + negativeCase,
                CalculatorService.ILLEGAL_ARGUMENT, service.calculateString(negativeCase));
        }
    }

}
