package com.charity.charityapi.config.jwt.impl;

import com.charity.charityapi.config.jwt.AuthenticationProvider;
import com.charity.charityapi.config.jwt.authority.JwtUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl  implements AuthenticationProvider {
  @Override
  public Authentication getAuthentication(JwtUser user){
    return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
  }

  @Override
  public void setAuthentication(Authentication authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    System.out.println("executed");
  }
}
