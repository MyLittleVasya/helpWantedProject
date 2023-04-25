package com.charity.charityapi.dto.mapper;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.UserPrivateDto;
import com.charity.charityapi.persistence.User;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

/**
 * Bold mapper for:
 * <p>{@link User}</p>
 * <p>{@link com.charity.charityapi.dto.UserDto}</p>
 * <p>{@link com.charity.charityapi.dto.UserPrivateDto}</p>
 */
@Component
public class UserDtoMapper {

  public UserDto userToDto(@Nonnull final User user) {
    final var userDto = UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .reputation(user.getReputation())
        .build();
    return userDto;
  }

  public UserPrivateDto userToPrivateDto(@Nonnull final User user) {
    final var userDto = UserPrivateDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .reputation(user.getReputation())
        .build();
    return userDto;
  }
}
