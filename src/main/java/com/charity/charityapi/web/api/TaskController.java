package com.charity.charityapi.web.api;

import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.request.CreateTaskRequest;
import com.charity.charityapi.persistence.User;
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
import org.springframework.web.bind.annotation.PutMapping;
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

  @PostMapping("/create")
  public ResponseEntity createTask(@NotNull @Valid final CreateTaskRequest request) {
    return null;
  }

  @GetMapping()
  public ResponseEntity getTasks(@RequestParam(name = "page") final long page,
                                 @RequestParam(name = "size") final long size,
                                 @RequestParam(name = "tags", required = false) final Set<String> tags) {
    return null;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity getTask(@PathVariable(name = "id") final long id) {
    return null;
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
  public ResponseEntity deleteTask(@PathVariable(name = "id") final long id) {
    return null;
  }

  @PostMapping("/addVolunteer/{id}")
  public ResponseEntity addVolunteer(@AuthenticationPrincipal User user) {
    return null;
  }

  @DeleteMapping("/deleteVolunteer{id}")
  public ResponseEntity deleteVolunteer(@PathVariable(name = "id") final long id,
                                        @RequestParam(name = "userId") final long userId) {
    return null;
  }

  @PostMapping("/finish/{id}")
  public ResponseEntity finishTask(@PathVariable(name = "id") final long id) {
    return null;
  }
}
