package com.wkoszycki.calculator;

import com.wkoszycki.calculator.exception.InvalidMathStringException;
import com.wkoszycki.calculator.integral.DefiniteIntegralParameters;
import com.wkoszycki.calculator.integral.AsynchronousIntegralService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("calculator")
@RequestScoped
public class CalculatorResource {

  private final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

  @Inject
  private CalculatorService calculatorService;

  public CalculatorResource() {
  }

  protected CalculatorResource(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  @POST
  @Path("/v1.0/calculate")
  @Produces("application/json")
  public Response calculate(@FormParam("mathString") String mathString) throws
                                                                        InvalidMathStringException {
    logger.info("Received message at Form param:" + mathString);
    final String result = calculatorService.calculateString(mathString);
    return Response.status(Response.Status.OK)
        .entity(result)
        .build();
  }

  @POST
  @Path("/v1.0/integral/ex")
  @Produces("application/json")
  public Response calculateEtoXintegral(DefiniteIntegralParameters parameters) throws
                                                                               InvalidMathStringException,
                                                                               ExecutionException,
                                                                               InterruptedException {

    final double integral = new AsynchronousIntegralService().calculateEtoX(parameters);
    return Response.status(Response.Status.OK)
        .entity(integral)
        .build();
  }

  @GET
  @Path("/v1.0/history")
  @Produces("application/json")
  public Response getHistory() {
    return Response.status(Response.Status.OK)
        .entity(calculatorService.getOperations())
        .build();
  }
}
