package com.charity.charityapi.persistence.repository;

import com.charity.charityapi.persistence.Volunteer;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
  Set<Volunteer> findAllByTaskId(long taskId);
}
