package com.example.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        log.error(e.getMessage(), e);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name() + ": " + e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({OperationException.class})
    public ResponseEntity<String> handleException(OperationException e) {
        log.error(e.getMessage(), e);

        return new ResponseEntity<>(e.getStatus().name() + ": " + e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e),
            e.getStatus());
    }

}