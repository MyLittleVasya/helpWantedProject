package com.charity.charityapi.dto;

import com.charity.charityapi.persistence.User;
import java.util.Set;
import lombok.Value;

/**
 * DTO for {@link com.charity.charityapi.persistence.Task}
 */
@Value
public class TaskDto {

  long id;

  String name;

  String shortDescription;

  String description;

  boolean finished;

  Set<String> tags;

  Set<UserDto> volunteers;

  UserDto author;
}
