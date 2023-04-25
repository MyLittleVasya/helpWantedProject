package com.charity.charityapi.config.jwt.impl;

import com.charity.charityapi.config.jwt.AuthenticationProvider;
import com.charity.charityapi.config.jwt.authority.JwtUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Authentication provider to update authentication context of request thread.
 */
@Component
public class AuthenticationProviderImpl  implements AuthenticationProvider {

  /**
   * Get authentication for current user.
   *
   * @param user user to create authentication for.
   * @return {@link Authentication} instance.
   */
  @Override
  public Authentication getAuthentication(JwtUser user){
    return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
  }

  /**
   * Update current thread context with authentication.
   *
   * @param authentication authentication details of current user.
   */
  @Override
  public void setAuthentication(Authentication authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    System.out.println("User was authenticated.");
  }
}
