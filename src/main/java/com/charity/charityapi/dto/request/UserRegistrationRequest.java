package com.charity.charityapi.dto.request;

import lombok.Value;

/**
 * User registration request body.
 */
@Value
public class UserRegistrationRequest {

  String username;

  String password;

  String email;
}
