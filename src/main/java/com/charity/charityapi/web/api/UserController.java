package com.charity.charityapi.web.api;

import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class UserController {
  private final UserService userService;
  @PostMapping("/create")
  public ResponseEntity<User> registerNewUser(@RequestBody @NotNull final
                                                 UserRegistrationRequest requestBody) {
    final var user = userService.createNewUser(requestBody);
    return ResponseEntity.ok(user);
  }

}
