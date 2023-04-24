package com.charity.charityapi.service;

import jakarta.annotation.Nonnull;

/**
 * Service for user authentication.
 */
public interface AuthenticationService {

  /**
   * Authenticate user by username and password.
   *
   * @param username string value of user login
   * @param password string value of user password
   * @return string value of authentication token.
   */
  String getAuthenticationToken(@Nonnull String username, @Nonnull String password);

}
