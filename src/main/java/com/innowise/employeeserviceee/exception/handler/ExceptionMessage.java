package com.innowise.employeeserviceee.exception.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class ExceptionMessage {
    LocalTime timestamp;
    int status;
    String error;
    String message;
    String path;
}