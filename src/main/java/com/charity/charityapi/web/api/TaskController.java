package com.charity.charityapi.web.api;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.request.CreateTaskRequest;
import com.charity.charityapi.dto.request.GetTasksRequest;
import com.charity.charityapi.dto.response.GetTasksResponse;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for {@link com.charity.charityapi.persistence.User}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks")
@Validated
public class TaskController {
  private final TaskService taskService;
  private final UserRepository userRepository;
  @PostMapping("/create")
  public ResponseEntity<TaskDto> createTask(@NotNull @Valid @RequestBody final CreateTaskRequest request,
                                            @AuthenticationPrincipal final JwtUser authUser) {
    final var user = userRepository.findById(authUser.getId());
    final var result = taskService.createTask(request, user);
    return ResponseEntity.ok(result);
  }
  //
  @GetMapping()
  public ResponseEntity<GetTasksResponse> getTasks(@RequestBody final GetTasksRequest request) {
    if (request.getTags() == null || request.getTags().isEmpty()) {
      final var result = taskService.getTasks(request);
      return ResponseEntity.ok(result);
    } else {
      final var result = taskService.getTasksContainingTags(request);
      return ResponseEntity.ok(result);
    }
  }
  //
  @GetMapping("{id}/get")
  public ResponseEntity<TaskDto> getTask(@PathVariable(name = "id") final long id) {
    final var result = taskService.getTask(id);
    return ResponseEntity.ok(result);
  }

  //
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
  public ResponseEntity deleteTask(@PathVariable(name = "id") final long id) {
    return null;
  }

  //
  @PostMapping("/{id}/addVolunteer")
  public ResponseEntity addVolunteer(@PathVariable(name = "id") final long id,
                                     @AuthenticationPrincipal final JwtUser user) {
    return null;
  }
  //
  @DeleteMapping("/{id}/deleteVolunteer")
  public ResponseEntity deleteVolunteer(@PathVariable(name = "id") final long id,
                                        @AuthenticationPrincipal final JwtUser user) {
    return null;
  }

  @PostMapping("/{id}/chooseExecutor/{executorId}")
  public ResponseEntity addExecutor(@PathVariable(name = "id") final long taskId,
                                    @PathVariable(name = "executorId") final long executorId) {
    return null;
  }

  @PostMapping("/{id}/finish")
  public ResponseEntity finishTask(@PathVariable(name = "id") final long id) {
    return null;
  }
}
