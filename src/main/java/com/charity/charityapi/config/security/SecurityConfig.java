package com.charity.charityapi.config.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import com.charity.charityapi.config.jwt.impl.JwtFilter;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtFilter jwtFilter;
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      @Nonnull final AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//    return new WebMvcConfigurer() {
//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//        registry
//            .addMapping("/**")
//            .allowedOrigins("http://localhost:3000");
//      }
//    };
//  }

  /**
   * Configuration of security.
   */
  @Bean
  public SecurityFilterChain filterChain(@Nonnull final HttpSecurity http) throws Exception {
    http
        //This should be merged because of /h2-console which uses frames
        .headers().frameOptions().sameOrigin()
        .and()
        // disable CSRF as we do not serve browser clients
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/login", "/users/create").permitAll()
        .requestMatchers(toH2Console()).permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic().disable()
        .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
        // make sure we use stateless session, session will not be used to store user's state
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
}
