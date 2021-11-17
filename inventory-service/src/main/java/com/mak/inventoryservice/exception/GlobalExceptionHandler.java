package com.mak.inventoryservice.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ProductNotFoundException ex, WebRequest request) {
        log.trace("ProductNotFoundException " + ex.getMessage()); // log exception to logs and send just message to user
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Product not found !!!", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        log.trace("MethodArgumentNotValidException " + ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Input invalid", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        log.trace("Exception " + ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Server Error, try another time ", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}