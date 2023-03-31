package com.innowise.employeeserviceee.exception;


public class NoSuchRecordException extends AbstractException {

    public NoSuchRecordException(int code) {
        super(code);
    }

    public NoSuchRecordException(int code, String message) {
        super(code);
    }

    public NoSuchRecordException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
