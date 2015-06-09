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

@Path("calculator/v1.0")
@RequestScoped
public class CalculatorResource {

  private final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

  @Inject
  private CalculatorService calculatorService;

  public CalculatorResource() {
  }

  public CalculatorResource(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  @POST
  @Path("/calculate")
  public Response calculate(@FormParam("mathString") String mathString) throws
                                                                        InvalidMathStringException {
    logger.info("Received message at Form param:" + mathString);
    final String result = calculatorService.calculateString(mathString);
    return Response.status(200)
        .entity(result)
        .type(MediaType.TEXT_PLAIN)
        .build();
  }

  @GET
  @Path("/getHistory")
  public Response getHistory() {
    return Response.status(200)
        .entity(calculatorService.getOperations())
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
