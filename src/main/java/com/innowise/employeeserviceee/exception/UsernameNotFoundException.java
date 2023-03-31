package com.innowise.employeeserviceee.exception;

public class UsernameNotFoundException extends AbstractException {
    public UsernameNotFoundException(int code) {
        super(code);
    }

    public UsernameNotFoundException(int code, String message) {
        super(code);
    }

    public UsernameNotFoundException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
