package com.charity.charityapi.config.jwt;

import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Jwt token provider class.
 *
 * <p>Generates jwt auth token for user or validates token.</p>
 */
public interface JwtTokenProvider {

  /**
   *Create auth jwt token for user.
   *
   * @param user entity to create token for.
   * @return String value of jwt token.
   */
  String createAuthToken(@NotNull User user);

  /**
   * Validate token by sign or expiration date.
   *
   * @param token token value to validate.
   */
  void validateToken(@NotBlank String token);

  /**
   * Parse UserRole from token.
   *
   * @param token token to parse.
   * @return UserRole enum value.
   */
  UserRole parseUserRole(@NotBlank String token);
}
