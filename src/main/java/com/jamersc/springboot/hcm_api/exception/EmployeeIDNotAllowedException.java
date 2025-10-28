package com.jamersc.springboot.hcm_api.exception;

public class EmployeeIDNotAllowedException extends RuntimeException {
    public EmployeeIDNotAllowedException(String message) {
        super(message);
    }

    public EmployeeIDNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeIDNotAllowedException(Throwable cause) {
        super(cause);
    }
}
