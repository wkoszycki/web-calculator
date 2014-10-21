package com.cinemacity.calculator.rest;

import com.cinemacity.calculator.TestUtil;
import com.cinemacity.calculator.service.CalculatorService;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class CalculatorResourceTest {

    CalculatorResource instance;

    public CalculatorResourceTest() {
        this.instance = new CalculatorResource();
        CalculatorService calculatorService = new CalculatorService();
        calculatorService.init();
        instance.setCalculatorService(calculatorService);
    }

    @Test
    public void testPositiveCases() {
        for (String positiveCase : TestUtil.positiveCases) {
            Response response = instance.calculate(positiveCase);
            assertEquals("httpStatus code differs:" + positiveCase,
                201, response.getStatus());
        }
    }

    @Test
    public void testNegativeCases() {
        for (String negativeCase : TestUtil.negativeCases) {
            Response response = instance.calculate(negativeCase);
            assertEquals("httpStatus code differs:" + negativeCase,
                401, response.getStatus());
        }
    }

}
