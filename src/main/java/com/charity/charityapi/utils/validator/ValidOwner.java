package com.charity.charityapi.utils.validator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.charity.charityapi.persistence.User;
import com.charity.charityapi.utils.validator.UserAccessValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Constraint validation annotation for {@link User}.
 *
 * <p>Annotation validates whether user can access certain user data.</p>
 *
 * <p>Validation mechanism is provided by {@link UserAccessValidator}</p>
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = UserAccessValidator.class)
@Documented
public @interface ValidOwner {
  /**
   * Error message.
   */
  String message() default "You have no right to access this data.";

  /**
   * Group attribute.
   */
  Class<?>[] groups() default {};

  /**
   * Payload attribute.
   */
  Class<? extends Payload>[] payload() default {};
}
