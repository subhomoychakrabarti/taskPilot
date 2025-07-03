package com.taskpilot.registration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginValidationFailedException.class)
    public ResponseEntity<String> handleLoginException(LoginValidationFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
