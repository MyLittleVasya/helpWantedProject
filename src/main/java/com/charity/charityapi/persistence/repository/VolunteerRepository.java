package com.charity.charityapi.persistence.repository;

import com.charity.charityapi.persistence.Volunteer;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
  Set<Volunteer> findAllByTaskId(long taskId);

  boolean existsByUserIdAndTaskId(long userId, long taskId);

  Volunteer findByUserIdAndTaskId(long userId, long taskId);

  void deleteById(long id);
}
