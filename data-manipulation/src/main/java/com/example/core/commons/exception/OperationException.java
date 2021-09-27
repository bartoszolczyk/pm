package com.example.core.commons.exception;

import com.example.core.commons.exception.messages.AccountExceptionMessage;
import org.springframework.http.HttpStatus;

public class OperationException extends ErrorStubException {

    public OperationException(Enum<?> errorException, HttpStatus status) {
        super(errorException, status);
    }

    public OperationException(AccountExceptionMessage errorException, HttpStatus status, Exception e) {
        super(errorException, status,e);
    }
}
