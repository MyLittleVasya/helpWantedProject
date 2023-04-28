package com.charity.charityapi.service.impl;

import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.handler.exception.UserNotFoundException;
import com.charity.charityapi.handler.exception.WrongCredentialsException;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.service.AuthenticationService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuthenticationService}.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Generate authentication token using given credentials.
   *
   * @param username string value of user login
   * @param password string value of user password
   * @return string value of authentication jwt token.
   */
  @Override
  public String getAuthenticationToken(@Nonnull final String username, @Nonnull final String password) {
    if (!isCredentialsValid(username, password)) {
      throw new WrongCredentialsException("Provided credentials are wrong. Authentication is denied.");
    }
    final var user = userRepository.findByUsername(username);

    final var authenticationToken = jwtTokenProvider.createAuthToken(user);

    return authenticationToken;
  }

  /**
   * Check validity of user credentials.
   *
   * @param username string value of user login
   * @param password string value of user password
   * @return boolean value of credentials validity.
   */
  private boolean isCredentialsValid(@Nonnull final String username, @Nonnull final String password) {
    final var user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UserNotFoundException(String.format("User with username: %s is not found.", username));
    }

    return passwordEncoder.matches(password, user.getPassword());
  }
}
