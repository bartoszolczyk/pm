package com.example.commons.exception;

import com.example.commons.exception.messages.SystemExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OperationException extends RuntimeException {

    private final SystemExceptionMessage errorException;

    private final HttpStatus status;

    public OperationException(SystemExceptionMessage errorException, HttpStatus status) {
        this.errorException = errorException;
        this.status = status;
    }

    public OperationException(SystemExceptionMessage errorException, HttpStatus status, Throwable e) {
        this.errorException = errorException;
        this.status = status;
        super.initCause(e);
    }
}
