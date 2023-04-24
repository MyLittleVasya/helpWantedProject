package com.charity.charityapi.handler.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 869612297610622829L;

  public UserNotFoundException(String msg) {
    super(msg);
  }

}
