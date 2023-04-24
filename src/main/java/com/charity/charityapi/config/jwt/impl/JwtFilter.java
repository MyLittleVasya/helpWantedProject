package com.charity.charityapi.config.jwt.impl;

import com.charity.charityapi.config.jwt.AuthenticationProvider;
import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.persistence.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final AuthenticationProvider authenticationProvider;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    final var uri = request.getRequestURI();
    if (uri.equals("/login") || uri.equals("/users/create")){
      filterChain.doFilter(request,response);
      return;
    }
    final var token = request.getHeader("Authorization");

    if(token == null || token.isEmpty()) {
      response.sendError(401);
    }
    final var jwtToken = token.substring(7);
    final var isTokenValid = jwtTokenProvider.validateToken(jwtToken);
    final var parsedUsername = jwtTokenProvider.parseUsername(jwtToken);
    final var user = userRepository.findByUsername(parsedUsername);
    if (user==null){
    response.sendError(401);
    }
    final var jwtUser = new JwtUser(
      user.getId(),
      user.getUsername(),
      user.getUserRole());
    final var authentication =
      authenticationProvider.getAuthentication(jwtUser);

    if (authentication != null){
    authenticationProvider.setAuthentication(authentication);
    }

    filterChain.doFilter(request,response);
  }
}
