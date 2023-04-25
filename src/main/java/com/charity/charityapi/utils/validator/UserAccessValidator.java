package com.charity.charityapi.utils.validator;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.handler.exception.AccessDeniedException;
import com.charity.charityapi.persistence.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Bean that validates whether user can access data of another user.
 *
 * <p>Provides mechanism for {@link ValidOwner}.</p>
 */
@Component
@RequiredArgsConstructor
public class UserAccessValidator implements ConstraintValidator<ValidOwner, Long> {

  private final UserRepository userRepository;

  /**
   * Validate whether user can access another user`s data.
   * @param id target user id.
   * @param context context in which the constraint is evaluated
   *
   * @return boolean value of condition.
   */
  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    final var requestOwner = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final var requestOwnerId = requestOwner.getId();
    if (id == requestOwnerId) {
      return true;
    }
    throw new AccessDeniedException("You have no right to access this data");
  }
}
