package com.charity.charityapi.web.api;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.request.CreateTaskRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity getTasks(@RequestParam(name = "page") final long page,
                                 @RequestParam(name = "size") final long size,
                                 @RequestParam(name = "tags", required = false) final Set<String> tags) {
    return null;
  }
  //
  @GetMapping("/get/{id}")
  public ResponseEntity getTask(@PathVariable(name = "id") final long id) {
    return null;
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
