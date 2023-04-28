package com.charity.charityapi.persistence.repository;

import com.charity.charityapi.persistence.Task;
import com.charity.charityapi.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for {@link User}.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
  Task findById(long id);
}
