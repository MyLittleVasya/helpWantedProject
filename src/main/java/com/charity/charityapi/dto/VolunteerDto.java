package com.charity.charityapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VolunteerDto {
  long id;

  String username;

  long reputation;

  long user_id;

  boolean isExecutor;
}
