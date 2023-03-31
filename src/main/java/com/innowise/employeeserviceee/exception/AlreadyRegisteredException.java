package com.innowise.employeeserviceee.exception;


public class AlreadyRegisteredException extends AbstractException {

    public AlreadyRegisteredException(int code) {
        super(code);
    }

    public AlreadyRegisteredException(int code, String message) {
        super(code);
    }

    public AlreadyRegisteredException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
