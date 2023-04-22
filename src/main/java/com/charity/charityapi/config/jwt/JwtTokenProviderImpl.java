package com.charity.charityapi.config.jwt;

import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.UserRole;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProvider{

  @Value("${jwtTokenSecretKey}")
  private final String secretKey;

  @Override
  public String createAuthToken(User user) {
    final var expirationDate = Date.from
        (LocalDate
            .now()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
        );
    final var userRole = user.getUserRoles().stream().findFirst();
    final var token = Jwts.builder()
        .setExpiration(expirationDate)
        .claim("role", userRole.toString().toUpperCase())
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
    return token;
  }

  @Override
  public void validateToken(String token) throws JwtException {
    Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);;
  }

  @Override
  public UserRole parseUserRole(String token) {
    final var claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    final var userRole = UserRole.valueOf(claims.get("role").toString());
    return userRole;
  }
}
