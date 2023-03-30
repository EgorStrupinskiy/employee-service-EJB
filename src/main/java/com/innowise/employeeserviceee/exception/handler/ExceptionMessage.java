package com.innowise.employeeserviceee.exception.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ExceptionMessage {
    int httpStatus;
    String errorMessage;
}