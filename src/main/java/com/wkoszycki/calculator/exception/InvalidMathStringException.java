package com.wkoszycki.calculator.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class InvalidMathStringException extends WebApplicationException {

  public InvalidMathStringException() {
    super(buildResponse("Math string is invalid that's all we know..."));
  }
  public InvalidMathStringException(String message) {
    super(buildResponse(message));
  }

  private static Response buildResponse(String message) {
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(message)
        .build();
  }
}
