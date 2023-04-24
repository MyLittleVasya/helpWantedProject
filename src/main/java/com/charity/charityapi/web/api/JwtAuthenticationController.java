package com.charity.charityapi.web.api;

import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.UserRole;
import com.charity.charityapi.persistence.repository.UserRepository;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  @PostMapping("/login")
  public ResponseEntity login(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password){
    final var user = new User(1L,"1", "1",UserRole.USER);
    userRepository.save(user);
    final var token = jwtTokenProvider.createAuthToken(user);

    return ResponseEntity.ok(token);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('USER')")
  public ResponseEntity test(){
    return ResponseEntity.ok().build();
  }

}
