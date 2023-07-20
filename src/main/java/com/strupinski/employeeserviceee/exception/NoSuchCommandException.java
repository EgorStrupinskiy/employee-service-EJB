package com.strupinski.employeeserviceee.exception;


import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class NoSuchCommandException extends AbstractException {

    private static final int STATUS = HttpServletResponse.SC_NOT_FOUND;
    private static final String ERROR = "Not Found";
    private static final String MESSAGE = "No command with this name!";

    public NoSuchCommandException(String path) {
        super(LocalTime.now(), STATUS, ERROR, path);
    }

    public NoSuchCommandException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public NoSuchCommandException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }
}
