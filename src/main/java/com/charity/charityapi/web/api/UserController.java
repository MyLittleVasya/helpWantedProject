package com.charity.charityapi.web.api;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.UserPrivateDto;
import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.service.UserService;
import com.charity.charityapi.utils.validator.ValidOwner;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/users")
@Validated
public class UserController {
  private final UserService userService;

  @PostMapping("/create")
  public ResponseEntity<UserDto> registerNewUser(@RequestBody @NotNull final
                                              UserRegistrationRequest requestBody) {
    final var user = userService.createNewUser(requestBody);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/private/{id}")
  public ResponseEntity<UserPrivateDto> getPrivateUser(@PathVariable(name = "id") @NotNull @ValidOwner final Long id) {
    final var user = userService.getPrivateUser(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") @NotNull final Long id) {
    final var user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserDto> deleteUser(@PathVariable(name = "id") @NotNull final Long id) {
    final var user = userService.deleteUser(id);
    return ResponseEntity.ok(user);
  }

}
