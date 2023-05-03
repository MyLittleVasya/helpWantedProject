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
import java.util.Set;
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
  @PostMapping()
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
  @DeleteMapping("{id}/delete")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
  public ResponseEntity<TaskDto> deleteTask(@PathVariable(name = "id") final long id) {
    final var result = taskService.deleteTask(id);
    return ResponseEntity.ok(result);
  }

  //
  @PostMapping("/{id}/addVolunteer")
  public ResponseEntity<TaskDto> addVolunteer(@PathVariable(name = "id") final long taskId,
                                     @AuthenticationPrincipal final JwtUser user) {
    final var result = taskService.addVolunteer(taskId, user);
    return ResponseEntity.ok(result);
  }
  //
  @DeleteMapping("/{id}/deleteVolunteer")
  public ResponseEntity<TaskDto> deleteVolunteer(@PathVariable(name = "id") final long taskId,
                                        @AuthenticationPrincipal final JwtUser user) {
    final var result = taskService.deleteVolunteer(taskId, user);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/{id}/chooseExecutor/{executorId}")
  public ResponseEntity<TaskDto> addExecutor(@PathVariable(name = "id") final long taskId,
                                    @PathVariable(name = "executorId") final long executorId,
                                    @AuthenticationPrincipal final JwtUser user) {
    final var result = taskService.addExecutor(taskId, executorId, user);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/{id}/finish")
  public ResponseEntity<TaskDto> finishTask(@PathVariable(name = "id") final long id,
                                   @AuthenticationPrincipal final JwtUser user) {
    final var result = taskService.finishTask(id, user);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/createdBy/{userId}")
  public ResponseEntity<Set<TaskDto>> getTasksCreatedByUser(@PathVariable(name = "userId") final long id) {
    final var result = taskService.getTasksCreatedByUser(id);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/volunteeredBy/{userId}")
  public ResponseEntity<Set<TaskDto>> getUserVolunteerRequests(@PathVariable(name = "userId") final long id) {
    final var result = taskService.getUserVolunteerRequests(id);
    return ResponseEntity.ok(result);
  }
}
