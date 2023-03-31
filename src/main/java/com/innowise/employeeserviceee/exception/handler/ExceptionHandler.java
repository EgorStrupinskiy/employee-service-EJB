package com.innowise.employeeserviceee.exception.handler;

import com.innowise.employeeserviceee.exception.AbstractException;
import com.innowise.employeeserviceee.exception.AlreadyRegisteredException;
import com.innowise.employeeserviceee.exception.NoSuchCommandException;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandler {
    public static void handle(AbstractException exception, HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.setStatus(exception.getErrorCode());
            response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getLocalizedMessage())));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

}