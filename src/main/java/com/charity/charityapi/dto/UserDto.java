package com.charity.charityapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
  long id;

  String username;

  long reputation;
}
