package com.innowise.employeeserviceee.exception;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class UsernameNotFoundException extends AbstractException {
    private static final int STATUS = HttpServletResponse.SC_BAD_REQUEST;
    private static final String ERROR = "Bad Request";

    public UsernameNotFoundException(String path) {
        super(LocalTime.now(), STATUS, ERROR, "", path);
    }

    public UsernameNotFoundException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public UsernameNotFoundException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }

}
