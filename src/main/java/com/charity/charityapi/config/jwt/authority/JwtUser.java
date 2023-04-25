package com.charity.charityapi.config.jwt.authority;

import com.charity.charityapi.persistence.UserRole;
import java.util.Collection;
import java.util.HashSet;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *  Entity that contains authentication info.
 *
 *  <p>Used to put userDetails to context.</p>
 */
@Value
public class JwtUser implements UserDetails {
  long id;

  String userName;

  UserRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    final var collection = new HashSet<UserRole>(){{
      add(role);
    }};
    return collection;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
