package com.innowise.employeeserviceee.exception;


public class NoSuchCommandException extends RuntimeException {

    public NoSuchCommandException() {
        super();
    }

    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
