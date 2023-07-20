package com.strupinski.employeeserviceee.exception;


import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class AlreadyRegisteredException extends AbstractException {

    private static final int STATUS = HttpServletResponse.SC_BAD_REQUEST;
    private static final String ERROR = "Bad Request";

    public AlreadyRegisteredException(String path) {
        super(LocalTime.now(), STATUS, ERROR, path);
    }

    public AlreadyRegisteredException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public AlreadyRegisteredException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }
}
