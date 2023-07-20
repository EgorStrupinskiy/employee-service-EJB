package com.strupinski.employeeserviceee.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionMessage {
    String timestamp;
    int status;
    String error;
    String message;
    String path;
}