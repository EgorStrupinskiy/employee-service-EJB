package com.innowise.employeeserviceee.exception.handler;

import com.innowise.employeeserviceee.exception.AbstractException;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandler {
    public static void handle(AbstractException exception, HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.setStatus(exception.getStatus());
            response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(exception.getTimeStamp(), exception.getStatus(),
                    exception.getError(), exception.getLocalizedMessage(), exception.getPath()))
            );
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

}