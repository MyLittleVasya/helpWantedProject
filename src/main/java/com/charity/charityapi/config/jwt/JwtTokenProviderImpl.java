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


@RequiredArgsConstructor
@Component
public class JwtTokenProviderImpl implements JwtTokenProvider{

  @Value("${jwtTokenSecretKey}")
  private String secretKey;

  @Override
  public String createAuthToken(User user) {
    final var expirationDate = Date.from
        (LocalDate
            .now()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
        );
    final var userRole = user.getUserRoles();
    final var token = Jwts.builder()
        .setExpiration(expirationDate)
        .claim("role", userRole.toString().toUpperCase())
        .claim("username", user.getUsername())
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    return token;
  }

  @Override
  public boolean validateToken(String token) throws JwtException {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token) != null;
  }

  @Override
  public String parseUsername(String token) {
    final var claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    final var userName = claims.get("username").toString();
    return userName;
  }
}
