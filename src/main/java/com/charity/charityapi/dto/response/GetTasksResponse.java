package com.charity.charityapi.dto.response;

import com.charity.charityapi.dto.TaskDto;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetTasksResponse {

  Set<TaskDto> tasks;

  boolean lastPage;

}
