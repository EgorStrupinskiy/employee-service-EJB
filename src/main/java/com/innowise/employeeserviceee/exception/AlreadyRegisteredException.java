package com.innowise.employeeserviceee.exception;


public class AlreadyRegisteredException extends RuntimeException {

    public AlreadyRegisteredException() {
        super();
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }

    public AlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
