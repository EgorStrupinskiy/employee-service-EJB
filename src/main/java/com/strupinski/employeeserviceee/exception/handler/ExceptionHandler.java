package com.strupinski.employeeserviceee.exception.handler;

import com.strupinski.employeeserviceee.exception.AbstractException;
import com.strupinski.employeeserviceee.util.JsonConverter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandler {

    public static void handle(AbstractException exception, HttpServletResponse response) {
        try {
            response.setStatus(exception.getStatus());
            response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(exception.getTimeStamp().toString(), exception.getStatus(),
                    exception.getError(), exception.getLocalizedMessage(), exception.getPath()))
            );
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

}