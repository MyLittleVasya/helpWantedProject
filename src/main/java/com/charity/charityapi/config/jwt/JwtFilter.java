package com.charity.charityapi.config.jwt;

import com.charity.charityapi.config.jwt.authority.*;
import com.charity.charityapi.persistence.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 */
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
  final var token = request.getHeader("Authorization");

  if(token == null || token.isEmpty()) {
    response.sendError(401);
  }
  final var isTokenValid = jwtTokenProvider.validateToken(token);

  final var user = userRepository.findByUsername(jwtTokenProvider.parseUsername(token));
  if (user==null){
    response.sendError(401);
  }




  }
}
