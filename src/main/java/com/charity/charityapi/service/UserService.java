package com.charity.charityapi.service;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.persistence.User;
import jakarta.annotation.Nonnull;

/**
 * Service for {@link com.charity.charityapi.persistence.User}.
 */
public interface UserService {

  /**
   * Create new user.
   *
   * @param registrationRequest user data.
   * @return DTO of created User.
   */
  User createNewUser(@Nonnull UserRegistrationRequest registrationRequest);

  /**
   * Update existing user.
   * @param userDto user data.
   * @return DTO of updated User.
   */
  User updateUser(@Nonnull UserDto userDto);

  /**
   * Delete existing user.
   * @param id string value of user id.
   * @return DTO of deleted User.
   */
  User deleteUser(@Nonnull String id);

}
