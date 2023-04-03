package com.innowise.employeeserviceee.exception;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class AuthenticationException extends AbstractException {
    private static final int STATUS = HttpServletResponse.SC_BAD_REQUEST;
    private static final String ERROR = "Bad Request";
    private static final String MESSAGE = "Error while authentication!";

    public AuthenticationException(String path) {
        super(LocalTime.now(), STATUS, ERROR, path);
    }

    public AuthenticationException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public AuthenticationException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }

}
