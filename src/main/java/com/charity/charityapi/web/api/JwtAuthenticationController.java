package com.charity.charityapi.web.api;

import com.charity.charityapi.dto.request.LoginRequest;
import com.charity.charityapi.dto.response.LoginResponse;
import com.charity.charityapi.service.AuthenticationService;
import com.charity.charityapi.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller that is responsible for user authentication.
 */
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

  private final AuthenticationService authenticationService;
  private final UserService userService;
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @NotNull final LoginRequest userRequest){
    final var token = authenticationService.getAuthenticationToken(
        userRequest.getUsername(),
        userRequest.getPassword());
    final var userDto = userService.getUserByUserName(userRequest.getUsername());
    final var response = new LoginResponse(token,userDto);

    return ResponseEntity.ok(response);
  }

  /**
   * todo remove this.
   */
  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity test(){
    return ResponseEntity.ok().build();
  }

  /**
   * todo remove this.
   */
  @GetMapping("/user")
  @PreAuthorize("hasAuthority('USER')")
  public ResponseEntity test2(){
    return ResponseEntity.ok().build();
  }

}
