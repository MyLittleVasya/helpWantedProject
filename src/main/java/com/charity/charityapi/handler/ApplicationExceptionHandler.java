package com.charity.charityapi.handler;

import com.charity.charityapi.handler.exception.AccessDeniedException;
import com.charity.charityapi.handler.exception.UserAlreadyExistsException;
import com.charity.charityapi.handler.exception.NotFoundException;
import com.charity.charityapi.handler.exception.WrongCredentialsException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for application.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle {@link NotFoundException}
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .date(new Date())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  /**
   * Handle {@link com.charity.charityapi.handler.exception.WrongCredentialsException}
   */
  @ExceptionHandler(WrongCredentialsException.class)
  public ResponseEntity<ErrorMessage> handleWrongCredentialsException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .status(HttpStatus.FORBIDDEN.value())
        .date(new Date())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
  }

  /**
   * Handle {@link AccessDeniedException}
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorMessage> handleConstraintViolationException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .status(HttpStatus.FORBIDDEN.value())
        .date(new Date())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
  }

  /**
   * Handle {@link (UserAlreadyExistsException}
   */
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .status(HttpStatus.FORBIDDEN.value())
        .date(new Date())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
  }
}
