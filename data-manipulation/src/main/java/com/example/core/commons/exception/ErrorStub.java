package com.example.core.commons.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
public interface ErrorStub extends Serializable {

    String getCode();

    String getMessage();
}
