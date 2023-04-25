package com.charity.charityapi.dto.request;

import lombok.Value;

/**
 * Login request body.
 */
@Value
public class LoginRequest {

  String username;

  String password;
}
