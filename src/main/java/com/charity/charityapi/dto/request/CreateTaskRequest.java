package com.charity.charityapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Value;

/**
 * Create task request body.
 */
@Value
public class CreateTaskRequest {

  @NotNull
  //@Size(min = 8, max = 20)
  String name;

  @NotNull
  //@Size(min = 64, max = 128)
  String shortDescription;

  @NotNull
  //@Size(min = 128, max = 255)
  String description;

  @NotEmpty
  //@Size(min = 1, max = 10)
  Set<String> tags;

}
