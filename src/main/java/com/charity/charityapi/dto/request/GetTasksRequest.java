package com.charity.charityapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetTasksRequest {

  @Min(0)
  int page;

  @Min(0)
  int size;

  @JsonProperty(required = false)
  Set<String> tags;
}
