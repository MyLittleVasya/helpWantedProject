package com.charity.charityapi.config.jwt;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import org.springframework.security.core.Authentication;

public class AuthenticationProviderImpl  implements AuthenticationProvider{
  @Override
  public Authentication getAuthentication(JwtUser user){

  }
}
