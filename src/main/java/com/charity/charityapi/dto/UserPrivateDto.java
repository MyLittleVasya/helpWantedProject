package com.charity.charityapi.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO that represents {@link com.charity.charityapi.persistence.User}.
 */
@Value
@Builder
public class UserPrivateDto {

  long id;

  String username;

  String email;

  long reputation;
}
