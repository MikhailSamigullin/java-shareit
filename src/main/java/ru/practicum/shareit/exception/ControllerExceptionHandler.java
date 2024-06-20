package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleValidationExceptions(
          MethodArgumentNotValidException exception, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    log.info("Получен запрос к эндпоинту: {}, ошибка: {}", request.getRequestURI(), exception.getMessage());
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors, LocalDateTime.now());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = {NotExistsException.class})
  public ResponseEntity<Object> handleNotFoundExceptions(
          RuntimeException exception, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    String errorMessage = exception.getMessage();
    errors.put("id", errorMessage);
    log.info("Получен запрос к эндпоинту: {}, ошибка: {}", request.getRequestURI(), exception.getMessage());
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), request.getRequestURI(), HttpStatus.NOT_FOUND.getReasonPhrase(), errors, LocalDateTime.now());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(value = {ConflictException.class})
  public ResponseEntity<Object> handleConflictExceptions(
          RuntimeException exception, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    String errorMessage = exception.getMessage();
    errors.put("id", errorMessage);
    log.info("Получен запрос к эндпоинту: {}, ошибка: {}", request.getRequestURI(), exception.getMessage());
    ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(), request.getRequestURI(), HttpStatus.CONFLICT.getReasonPhrase(), errors, LocalDateTime.now());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

}
