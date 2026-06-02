package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleException(ZooException exception) {

        log.error(exception.getMessage());

        ZooErrorResponse errorResponse = new ZooErrorResponse(
                exception.getMessage(),
                exception.getStatus().value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception) {

        log.error(exception.getMessage());

        ZooErrorResponse errorResponse = new ZooErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}