package com.charity.charityapi.handler.exception;

import java.io.Serial;

/**
 * Exception that is thrown when trying to create user
 * with credentials that already in use.
 */
public class UserAlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 466397302298336489L;

  public UserAlreadyExistsException(String msg) {
    super(msg);
  }
}
