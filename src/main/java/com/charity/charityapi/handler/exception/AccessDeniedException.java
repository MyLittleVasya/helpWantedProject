package com.charity.charityapi.handler.exception;

import java.io.Serial;

public class AccessDeniedException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 3063551918177858623L;

  public AccessDeniedException(String msg) {
    super(msg);
  }
}
