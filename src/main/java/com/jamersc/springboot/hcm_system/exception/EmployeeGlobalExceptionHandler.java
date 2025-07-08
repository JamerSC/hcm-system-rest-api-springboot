package com.jamersc.springboot.hcm_system.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeGlobalExceptionHandler.class);

    // add exception handler
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exc) {

        //  log exception internally
        logger.error("Unhandled exception: ", exc);

        // create employee id not found response
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value()); // 404 not found
        error.setMessage(exc.getMessage()); // exception message
        error.setTimestamp(System.currentTimeMillis()); // current time in ms

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeIDNotAllowedException(EmployeeIDNotAllowedException exc) {

        //  log exception internally
        logger.error("Unhandled exception: ", exc);

        // create employee id not allowed response
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400 bad request
        error.setMessage(exc.getMessage()); // exception message
        error.setTimestamp(System.currentTimeMillis()); // current time

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
