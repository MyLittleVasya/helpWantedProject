package com.charity.charityapi.persistence.repository;

import com.charity.charityapi.persistence.Task;
import com.charity.charityapi.persistence.User;
import java.util.Set;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for {@link User}.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
  Task findById(long id);

  Set<Task> findAllByTagsInIgnoreCaseAndFinished(PageRequest pageRequest, Set<String> set, boolean finished);

  Set<Task> findAllByFinishedOrderByIdDesc(PageRequest pageRequest, boolean finished);

  Task findTopByOrderByIdDesc();

  Set<Task> findByAuthorId(long authorId);


}
