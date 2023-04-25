package com.charity.charityapi.config.jwt;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import org.springframework.security.core.Authentication;

/**
 * Provides all steps of user authentication in Security context.
 */
public interface AuthenticationProvider {

  /**
   * Generates {@link Authentication} instance for user.
   *
   * @param user user create authentication for.
   * @return
   */
  Authentication getAuthentication(JwtUser user);

  /**
   * Set authentication for current user in thread security context.
   *
   * @param authentication instance of user authentication.
   */
  void setAuthentication(Authentication authentication);
}
