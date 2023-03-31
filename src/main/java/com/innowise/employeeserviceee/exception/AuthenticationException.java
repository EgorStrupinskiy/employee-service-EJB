package com.innowise.employeeserviceee.exception;

public class AuthenticationException extends AbstractException {
    public AuthenticationException(int code) {
        super(code);
    }

    public AuthenticationException(int code, String message) {
        super(code);
    }

    public AuthenticationException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
