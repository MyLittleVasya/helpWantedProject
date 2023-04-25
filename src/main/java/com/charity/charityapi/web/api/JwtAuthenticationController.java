package com.charity.charityapi.web.api;

import com.charity.charityapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

  private final AuthenticationService authenticationService;
  @PostMapping("/login")
  public ResponseEntity login(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password){
    final var token = authenticationService.getAuthenticationToken(username, password);

    return ResponseEntity.ok(token);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity test(){
    return ResponseEntity.ok().build();
  }

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('USER')")
  public ResponseEntity test2(){
    return ResponseEntity.ok().build();
  }

}
