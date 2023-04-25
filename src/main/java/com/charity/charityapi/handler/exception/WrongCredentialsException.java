package com.charity.charityapi.handler.exception;

import java.io.Serial;

/**
 * Exception that is thrown if credentials provided for
 * authentication is wrong.
 */
public class WrongCredentialsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -2469236484624868315L;

  public WrongCredentialsException(String msg) {
    super(msg);
  }
}
