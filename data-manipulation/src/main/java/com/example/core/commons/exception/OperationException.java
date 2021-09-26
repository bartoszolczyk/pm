package com.example.core.commons.exception;

import org.springframework.http.HttpStatus;

public class OperationException extends ErrorStubException {

    public OperationException(Enum<?> errorException, HttpStatus status) {
        super(errorException, status);
    }
}
