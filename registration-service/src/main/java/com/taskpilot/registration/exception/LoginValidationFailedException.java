package com.taskpilot.registration.exception;

public class LoginValidationFailedException extends RuntimeException {
    public LoginValidationFailedException(String message) {
        super(message);
    }
}
