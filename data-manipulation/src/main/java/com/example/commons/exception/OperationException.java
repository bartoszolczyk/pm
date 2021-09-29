package com.example.commons.exception;

import com.example.commons.exception.messages.SystemExceptionMessage;
import org.springframework.http.HttpStatus;

public class OperationException extends ErrorStubException {

    public OperationException(Enum<?> errorException, HttpStatus status) {
        super(errorException, status);
    }

    public OperationException(SystemExceptionMessage errorException, HttpStatus status, Exception e) {
        super(errorException, status,e);
    }
}
