package com.charity.charityapi.config.jwt;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import org.springframework.security.core.Authentication;

public interface AuthenticationProvider {
  Authentication getAuthentication(JwtUser user);
}
