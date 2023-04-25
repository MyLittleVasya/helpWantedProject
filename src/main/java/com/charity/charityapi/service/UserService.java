package com.charity.charityapi.service;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.UserPrivateDto;
import com.charity.charityapi.dto.request.UserRegistrationRequest;
import com.charity.charityapi.persistence.User;
import jakarta.annotation.Nonnull;

/**
 * Service for {@link com.charity.charityapi.persistence.User}.
 */
public interface UserService {

  /**
   * Get private user data.
   *
   * <p>Note: only owner of this data should access this data.</p>
   * <p>All other users including admin shouldn`t access data.</p>
   *
   * @param userId id of user to fetch data.
   * @return DTO with private user data.
   */
  @Nonnull
  UserPrivateDto getPrivateUser(long userId);

  /**
   * Get user data.
   *
   * <p>Note: only public data should be accessed</p>
   *
   * @param userId id of user to fetch data.
   * @return DTO with user data.
   */
  @Nonnull
  UserDto getUser(long userId);


  /**
   * Create new user.
   *
   * @param registrationRequest user data.
   * @return DTO of created User.
   */
  @Nonnull
  UserDto createNewUser(@Nonnull UserRegistrationRequest registrationRequest);

  /**
   * Delete existing user.
   * @param id string value of user id.
   * @return DTO of deleted User.
   */
  @Nonnull
  UserDto deleteUser(@Nonnull long id);

}
