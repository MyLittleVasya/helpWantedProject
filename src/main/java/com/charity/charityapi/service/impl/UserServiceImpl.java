package com.charity.charityapi.service.impl;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.UserPrivateDto;
import com.charity.charityapi.dto.mapper.UserDtoMapper;
import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.handler.exception.UserAlreadyExistsException;
import com.charity.charityapi.handler.exception.UserNotFoundException;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.UserRole;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.service.UserService;
import com.charity.charityapi.utils.validator.ValidOwner;
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
  private final UserDtoMapper userMapper;

  /**
   * Get private data of user.
   *
   * @param userId id of user to fetch data.
   * @return dto with private used data
   */
  @Nonnull
  @Override
  public UserPrivateDto getPrivateUser(@ValidOwner final long userId) {
    final var user = userRepository.findById(userId);
    if (user != null) {
      final var userDto = userMapper.userToPrivateDto(user);
      return userDto;
    }
    throw new UserNotFoundException("Cant access data of non existing user.");
  }

  /**
   * Get data of user.
   *
   * @param userId id of user to fetch data.
   * @return dto with user data.
   */
  @Nonnull
  @Override
  public UserDto getUser(final long userId) {
    final var user = userRepository.findById(userId);
    if (user != null) {
      final var userDto = userMapper.userToDto(user);
      return userDto;
    }
    throw new UserNotFoundException("Cant access data of non existing user.");
  }

  /**
   * Create new user from userDTO.
   * @param registrationRequest user data.
   * @return DTO of created user.
   */
  @Nonnull
  @Override
  public UserDto createNewUser(@Nonnull UserRegistrationRequest registrationRequest) {
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
    final var userDto = userMapper.userToDto(user);
    return userDto;
  }

  /**
   * Delete user by id.
   * @param userId string value of user id.
   * @return DTO of deleted user.
   */
  @Nonnull
  @Override
  public UserDto deleteUser(@Nonnull long userId) {
    final var user = userRepository.findById(userId);
    if (user != null) {
      final var userDto = userMapper.userToDto(user);
      userRepository.delete(user);
      return userDto;
    }
    throw new UserNotFoundException("Cant access data of non existing user.");
  }

  @Nonnull
  @Override
  public UserPrivateDto getUserByUserName(@Nonnull String name) {
    final var user = userRepository.findByUsername(name);
    if (user == null){
      throw new UserNotFoundException(
          String.format("Not found user with %s name not found",name));
    }
    return userMapper.userToPrivateDto(user);
  }
}
