package com.charity.charityapi.dto.mapper;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.UserPrivateDto;
import com.charity.charityapi.persistence.User;
import jakarta.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Bold mapper for:
 * <p>{@link User}</p>
 * <p>{@link com.charity.charityapi.dto.UserDto}</p>
 * <p>{@link com.charity.charityapi.dto.UserPrivateDto}</p>
 */
@Component
public class UserDtoMapper {

  /**
   * Map from {@link User} to {@link UserDto}.
   *
   * @param user user to map.
   * @return {@link UserDto} instance.
   */
  public UserDto userToDto(@Nonnull final User user) {
    final var userDto = UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .reputation(user.getReputation())
        .email(user.getEmail())
        .build();
    return userDto;
  }

  /**
   * Map from {@link User} to {@link UserPrivateDto}.
   *
   * @param user user to map.
   * @return {@link UserPrivateDto} instance.
   */
  public UserPrivateDto userToPrivateDto(@Nonnull final User user) {
    final var userDto = UserPrivateDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .reputation(user.getReputation())
        .build();
    return userDto;
  }

  /**
   * Map from {@link User} to {@link UserDto}.
   *
   * @param users user collection to map.
   * @return set of {@link UserDto}.
   */
  public Set<UserDto> userCollectionToUserDtoSet(@Nonnull final Collection<User> users) {
    final var resultSet = new HashSet<UserDto>();
    for (final var user: users) {
      resultSet.add(userToDto(user));
    }
    return resultSet;
  }
}
