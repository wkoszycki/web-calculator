package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Calculator Rest Service.
 */
@Path("calculator/v1.0")
@RequestScoped
public class CalculatorResource {

  Logger logger = LoggerFactory.getLogger(CalculatorService.class);

  private CalculatorService calculatorService;

  @Inject
  public CalculatorResource(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

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
      logger.info("Received message at Form param:" + mathString);
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

  @GET
  @Path("/getAllPreviousOperations")
  public Response getAllPreviousOperations() {
    return Response.status(200)
        .entity(calculatorService.getOperations())
        .type(MediaType.TEXT_PLAIN)
        .build();
  }
}
