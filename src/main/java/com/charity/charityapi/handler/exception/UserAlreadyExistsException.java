package com.charity.charityapi.handler.exception;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 466397302298336489L;

  public UserAlreadyExistsException(String msg) {
    super(msg);
  }
}
