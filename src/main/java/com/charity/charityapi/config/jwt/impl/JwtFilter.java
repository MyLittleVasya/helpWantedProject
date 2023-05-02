package com.charity.charityapi.config.jwt.impl;

import com.charity.charityapi.config.jwt.AuthenticationProvider;
import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.persistence.repository.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT authentication filter.
 *
 * <p>Filters every incoming request by JWT token.</p>
 * <p>Only public endpoints can be accessed without filtering.</p>
 * <p>
 *   After successful authentication context of current thread is updated
 *   with current user so that Spring Authorities could work.
 * </p>
 * <p>If authentication is failed 401 status is returned.</p>
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final AuthenticationProvider authenticationProvider;

  /**
   * Filter incoming request.
   *
   * @param request client`s request.
   * @param response server`s response.
   * @param filterChain chain of server security filters.
   * @throws ServletException exception thrown because of servlet issues.
   * @throws IOException exception to input/output operations.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    if (isPublicEndpoint(request) || request.getMethod().equals("OPTIONS")){
      filterChain.doFilter(request,response);
      return;
    }
    else {
      final var jwtToken = parseAuthToken(request, response);
      setAuthentication(jwtToken, response);

      filterChain.doFilter(request,response);
    }

    filterChain.doFilter(request,response);
  }


  /**
   * Check whether public endpoint is requested.
   *
   * @param request client`s request.
   * @return boolean value of condition.
   */
  private boolean isPublicEndpoint(@Nonnull final HttpServletRequest request) {
    final var uri = request.getRequestURI();
    return uri.equals("/login") || uri.equals("/users/create") || uri.contains("h2");
  }

  /**
   * Parse authentication token and check whether it`s valid.
   *
   * @param request client`s request.
   * @param response server`s response.
   * @return string value of parsed token.
   * @throws IOException exception to input/output operations.
   */
  private String parseAuthToken(@Nonnull final HttpServletRequest request,
                                         @Nonnull final HttpServletResponse response)
      throws IOException {
    final var token = request.getHeader("Authorization");

    if(token == null || token.isEmpty()) {
      response.sendError(401);
    }

    final var jwtToken = token.substring(7);
    final var isTokenValid = jwtTokenProvider.validateToken(jwtToken);
    if (!isTokenValid) {
      response.sendError(401);
    }
    return jwtToken;
  }

  /**
   * Set authentication for current user`s request.
   *
   * @param authToken authentication token provided by client.
   * @param response server`s response.
   * @throws IOException exception to input/output operations.
   */
  private void setAuthentication(@Nonnull final String authToken,
                                 @Nonnull final HttpServletResponse response) throws IOException {
    final var parsedUsername = jwtTokenProvider.parseUsername(authToken);
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
  }
}
