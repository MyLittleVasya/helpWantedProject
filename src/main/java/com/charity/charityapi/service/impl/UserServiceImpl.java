package com.charity.charityapi.service.impl;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.handler.exception.UserAlreadyExistsException;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.UserRole;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.service.UserService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation service of {@link UserService}.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  /**
   * Create new user from userDTO.
   * @param registrationRequest user data.
   * @return DTO of created user.
   */
  @Override
  public User createNewUser(@Nonnull UserRegistrationRequest registrationRequest) {
    final var user = User.builder()
        .username(registrationRequest.getUsername())
        .password(passwordEncoder.encode(registrationRequest.getPassword()))
        .userRole(UserRole.USER)
        .email(registrationRequest.getEmail())
        .build();
    if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
      throw new UserAlreadyExistsException(
          String.format("User with username %s and email %s already exists",
              user.getUsername(),
              user.getEmail())
      );
    }
    userRepository.save(user);
    return user;
  }

  /**
   * Update existing user.
   * @param userDto user data.
   * @return DTO of updated user.
   */
  @Override
  public User updateUser(@Nonnull UserDto userDto) {
    return null;
  }

  /**
   * Delete user by id.
   * @param id string value of user id.
   * @return DTO of deleted user.
   */
  @Override
  public User deleteUser(@Nonnull String id) {
    return null;
  }
}
