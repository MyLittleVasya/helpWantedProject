package com.charity.charityapi.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO that contains public data of {@link com.charity.charityapi.persistence.User}.
 */
@Value
@Builder
public class UserDto {
  long id;

  String username;

  String email;

  long reputation;
}
