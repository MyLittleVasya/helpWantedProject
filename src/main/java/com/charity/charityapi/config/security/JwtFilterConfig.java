package com.charity.charityapi.config.security;

import com.charity.charityapi.config.jwt.AuthenticationProvider;
import com.charity.charityapi.config.jwt.JwtFilter;
import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtFilterConfig {

  @Bean
  public JwtFilter jwtFilter(JwtTokenProvider jwtTokenProvider,
                             UserRepository userRepository,
                             AuthenticationProvider authenticationProvider){
    return new JwtFilter(jwtTokenProvider,userRepository,authenticationProvider);
  }
}
