package com.wkoszycki.calculator.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class InvalidMathExpressionException extends WebApplicationException {

  public InvalidMathExpressionException() {
    super(buildResponse("Math expression is invalid that's all we know..."));
  }

  public InvalidMathExpressionException(String message) {
    super(buildResponse(message));
  }

  private static Response buildResponse(String message) {
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(message)
        .build();
  }
}
