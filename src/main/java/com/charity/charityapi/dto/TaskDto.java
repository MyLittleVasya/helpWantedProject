package com.charity.charityapi.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link com.charity.charityapi.persistence.Task}
 */
@Value
@Builder
public class TaskDto {

  long id;

  String name;

  String shortDescription;

  String description;

  boolean finished;

  Set<String> tags;

  Set<VolunteerDto> volunteers;

  UserDto author;
}
