package com.innowise.employeeserviceee.exception.handler;

import com.innowise.employeeserviceee.exception.AlreadyRegisteredException;
import com.innowise.employeeserviceee.exception.NoSuchCommandException;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandler implements ServletContextListener {
    public static void handle(Throwable throwable, HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            if (throwable.getClass() == NoSuchCommandException.class) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_UNAUTHORIZED, throwable.getLocalizedMessage())));
            } else if (throwable.getClass() == NoSuchRecordException.class) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_BAD_REQUEST, throwable.getLocalizedMessage())));
            } else if (throwable.getClass() == AlreadyRegisteredException.class) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_BAD_REQUEST, throwable.getLocalizedMessage())));
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, throwable.getLocalizedMessage())));
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

}