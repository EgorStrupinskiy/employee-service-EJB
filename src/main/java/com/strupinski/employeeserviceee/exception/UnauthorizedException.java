package com.strupinski.employeeserviceee.exception;


import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class UnauthorizedException extends AbstractException {

    private static final int STATUS = HttpServletResponse.SC_UNAUTHORIZED;
    private static final String ERROR = "Unauthorized";
    private static final String MESSAGE = "This resource is forbidden for your role!";

    public UnauthorizedException(String path) {
        super(LocalTime.now(), STATUS, ERROR, path);
    }

    public UnauthorizedException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public UnauthorizedException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }

}
