package com.charity.charityapi.dto.request;

import lombok.Value;

@Value
public class LoginRequest {

  String username;

  String password;
}
