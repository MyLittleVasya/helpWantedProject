package com.charity.charityapi.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Main entity of up containing authentication and other data of user.
 */
@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  long id;

  @Column(name = "username", unique = true)
  String username;

  @Column(name = "password")
  String password;

  @Column(name = "user_role")
  @Enumerated(value = EnumType.STRING)
  UserRole userRole;

  @Column(name = "email", unique = true)
  String email;

  @Column(name = "reputation")
  long reputation;
}
