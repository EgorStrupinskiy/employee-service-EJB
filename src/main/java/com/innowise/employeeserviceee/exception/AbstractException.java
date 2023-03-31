package com.innowise.employeeserviceee.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AbstractException extends RuntimeException{
    private int errorCode;

    public AbstractException(int code) {
        this.errorCode = code;
    }

    public AbstractException(int code, String message) {
        super(message);
        this.errorCode = code;
    }

    public AbstractException(int code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
    }
}
