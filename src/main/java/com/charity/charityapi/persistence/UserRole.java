package com.charity.charityapi.persistence;

import org.springframework.security.core.GrantedAuthority;

/**
 * Roles of {@link User} that corresponds to different
 * levels of authority.
 */
public enum UserRole implements GrantedAuthority {
  USER, MODERATOR, ADMIN;

  @Override
  public String getAuthority() {
    return name();
  }
}
