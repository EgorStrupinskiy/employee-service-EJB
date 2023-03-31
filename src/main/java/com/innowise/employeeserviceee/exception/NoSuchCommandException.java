package com.innowise.employeeserviceee.exception;


public class NoSuchCommandException extends AbstractException {

    public NoSuchCommandException(int code) {
        super(code);
    }

    public NoSuchCommandException(int code, String message) {
        super(code);
    }

    public NoSuchCommandException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
