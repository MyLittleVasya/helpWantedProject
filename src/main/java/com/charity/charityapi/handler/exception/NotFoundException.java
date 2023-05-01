package com.charity.charityapi.handler.exception;

import java.io.Serial;

/**
 * Exception that is thrown if user is not found.
 */
public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 869612297610622829L;

  public NotFoundException(String msg) {
    super(msg);
  }

}
