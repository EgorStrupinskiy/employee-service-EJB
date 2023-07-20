package com.strupinski.employeeserviceee.exception;


import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalTime;

public class ServerException extends AbstractException {

    private static final int STATUS = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    private static final String ERROR = "Internal server error";

    public ServerException(String path) {
        super(LocalTime.now(), STATUS, ERROR, path);
    }

    public ServerException(String path, String message) {
        super(LocalTime.now(), STATUS, ERROR, path, message);
    }

    public ServerException(String path, String message, Throwable cause) {
        super(LocalTime.now(), STATUS, ERROR, path, message, cause);
    }
}
