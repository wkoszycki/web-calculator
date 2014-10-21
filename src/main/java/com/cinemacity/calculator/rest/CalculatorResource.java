package com.cinemacity.calculator.rest;

import com.cinemacity.calculator.exception.InvalidMathStringException;
import com.cinemacity.calculator.service.CalculatorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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
        String result;
        int httpStatusCode = 200;
        try {
            logger.info("got message at Form param:" + mathString);
            result = calculatorService.calculateString(mathString);
        } catch (InvalidMathStringException ex) {
            result = ex.getMessage();
            httpStatusCode = 400;
        }
        return Response.status(httpStatusCode)
            .entity(result)
            .type(MediaType.TEXT_PLAIN)
            .build();
    }

    /**
     * Prints history
     *
     * @return history string.
     */
    @GET
    @Path("/printHistory")
    public Response printHistory() {
        return Response.status(200)
            .entity(calculatorService.printHistory())
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
