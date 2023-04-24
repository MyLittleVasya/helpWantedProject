package com.charity.charityapi.dto.request;

import lombok.Value;

@Value
public class UserRegistrationRequest {

  String username;

  String password;

  String email;
}
