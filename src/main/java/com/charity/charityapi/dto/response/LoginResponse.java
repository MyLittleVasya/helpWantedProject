package com.charity.charityapi.dto.response;

import com.charity.charityapi.dto.UserPrivateDto;
import lombok.Value;

/**
 * Authentication body response
 */
@Value
public class LoginResponse {
  String token;
  UserPrivateDto user;
}
