package com.crimelens.crimelens_query.config;

import com.crimelens.crimelens_query.dto.error.ApiError;
import com.crimelens.crimelens_query.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleIllegalArgument(IllegalArgumentException ex) {
    return new ApiError("BAD_REQUEST", ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleNotFound(ResourceNotFoundException ex) {
    return new ApiError("NOT_FOUND", ex.getMessage());
  }
}
