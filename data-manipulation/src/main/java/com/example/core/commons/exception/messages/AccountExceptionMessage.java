package com.example.core.commons.exception.messages;

import com.example.core.commons.exception.ErrorStub;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountExceptionMessage implements ErrorStub {

    PLAYER_CREATION_EXCEPTION("PL_1", "Player Creation Exception"),
    PLAYER_NOT_FOUND("PL_2", "Player Not found "),
    PLAYER_UPDATE_EXCEPTION("PL_3", "Player Update Exception"),
    PLAYER_DELETE_EXCEPTION("PL_4", "Player Delete Exception");

    private final String code;
    private final String message;

}