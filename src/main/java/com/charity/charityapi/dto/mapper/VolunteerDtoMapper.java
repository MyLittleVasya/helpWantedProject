package com.charity.charityapi.dto.mapper;

import com.charity.charityapi.dto.UserDto;
import com.charity.charityapi.dto.VolunteerDto;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.Volunteer;
import com.charity.charityapi.persistence.repository.UserRepository;
import jakarta.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Bold mapper for:
 * <p>{@link com.charity.charityapi.persistence.Volunteer}</p>
 * <p>{@link User}</p>
 * <p>{@link com.charity.charityapi.dto.UserDto}</p>
 *  * <p>{@link com.charity.charityapi.dto.VolunteerDto}</p>
 */
@Component
@RequiredArgsConstructor
public class VolunteerDtoMapper {
  private final UserRepository userRepository;
  VolunteerDto volunteerToVolunteerDto(@Nonnull final Volunteer volunteer) {
    final var dto = VolunteerDto.builder()
        .id(volunteer.getId())
        .user_id(volunteer.getUser().getId())
        .username(volunteer.getUser().getUsername())
        .isExecutor(volunteer.isExecutor())
        .reputation(volunteer.getUser().getReputation())
        .build();
    return dto;
  }

  /**
   * Map from {@link com.charity.charityapi.persistence.Volunteer} to {@link com.charity.charityapi.dto.VolunteerDto}.
   *
   * @param volunteers collection to map.
   * @return set of {@link VolunteerDto}.
   */
  public Set<VolunteerDto> volunteerCollectionToVolunteerDtoSet(@Nonnull final Collection<Volunteer> volunteers) {
    final var resultSet = new HashSet<VolunteerDto>();
    for (final var volunteer: volunteers) {
      resultSet.add(volunteerToVolunteerDto(volunteer));
    }
    return resultSet;
  }
}
