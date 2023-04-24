package com.charity.charityapi.handler.exception;

import java.io.Serial;

public class WrongCredentialsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -2469236484624868315L;

  public WrongCredentialsException(String msg) {
    super(msg);
  }
}
