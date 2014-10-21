package com.cinemacity.calculator.rest;

import com.cinemacity.calculator.service.CalculatorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JAX-RS Resource for calculator.
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
@Path("calculator/v1.0")
@RequestScoped
public class CalculatorResource {

    Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Inject
    private CalculatorService calculatorService;

    /**
     * JAX-RS Interface for value calculations.
     *
     * @param mathString Mathematical String e.g 2+2*(9/3)
     * @return result of calculation
     */
    @POST
    @Path("/calculate")
    public Response calculate(@FormParam("mathString") String mathString) {
        logger.info("got message at Form param:" + mathString);
        String result = calculatorService.calculateString(mathString);
        int httpStatusCode = CalculatorService.getILLEGAL_ARGUMENT().equals(result) ? 401 : 201;
        return Response.status(httpStatusCode)
            .entity(String.valueOf(result))
            .type(MediaType.TEXT_PLAIN)
            .build();
    }

    /**
     * Use only for unit testing.
     *
     * @param calculatorService instance
     */
    protected void setCalculatorService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

}
