package com.charity.charityapi.persistence.repository;

import com.charity.charityapi.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findById(long id);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
