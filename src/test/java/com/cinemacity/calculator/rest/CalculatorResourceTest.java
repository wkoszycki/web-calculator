package com.cinemacity.calculator.rest;

import com.cinemacity.calculator.TestUtil;
import com.cinemacity.calculator.service.CalculatorService;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class CalculatorResourceTest {

    CalculatorResource instance;

    public CalculatorResourceTest() {
    }

    @Before
    public void setUp() {
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
                200, response.getStatus());
        }
    }

    @Test
    public void testNegativeCases() {
        for (String negativeCase : TestUtil.negativeCases) {
            Response response = instance.calculate(negativeCase);
            assertEquals("httpStatus code differs:" + negativeCase,
                400, response.getStatus());
        }
    }

}
