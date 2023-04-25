package com.charity.charityapi.config.jwt.impl;

import com.charity.charityapi.config.jwt.JwtTokenProvider;
import com.charity.charityapi.persistence.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link JwtTokenProvider}.
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

  /**
   * Secret signing key for jwt tokens.
   */
  @Value("${jwtTokenSecretKey}")
  private String secretKey;

  /**
   * Create auth token for authenticated user.
   *
   * @param user entity to create token for.
   * @return string value of jwt token.
   */
  @Override
  public String createAuthToken(User user) {
    final var expirationDate = getCurrentDate();

    final var userRole = user.getUserRole();

    final var token = Jwts.builder()
        .setExpiration(expirationDate)
        .claim("role", userRole.toString().toUpperCase())
        .claim("username", user.getUsername())
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    return token;
  }

  /**
   * Validate whether token is valid.
   *
   * @param token token value to validate.
   * @return boolean value of condition.
   * @throws JwtException thrown if jwt token is not valid.
   */
  @Override
  public boolean validateToken(String token) throws JwtException {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token) != null;
  }

  /**
   * Parse user`s login from jwt token.
   *
   * @param token token to parse.
   * @return string value of username.
   */
  @Override
  public String parseUsername(String token) {
    final var claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    final var userName = claims.get("username").toString();
    return userName;
  }

  /**
   * Get current utc date.
   * @return {@link Date} instance of current date.
   */
  private Date getCurrentDate() {
    final var currentDate = Date.from
        (LocalDate
            .now()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
        );
    return currentDate;
  }
}
