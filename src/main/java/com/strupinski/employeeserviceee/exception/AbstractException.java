package com.strupinski.employeeserviceee.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractException extends RuntimeException {

    private final LocalTime timeStamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;


    public AbstractException(LocalTime timeStamp, int status, String error, String path) {
        super();
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = "No message";
        this.path = path;
    }

    public AbstractException(LocalTime timeStamp, int status, String error, String path, String message) {
        super(message);
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public AbstractException(LocalTime timeStamp, int status, String error, String path, String message, Throwable cause) {
        super(message, cause);
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
