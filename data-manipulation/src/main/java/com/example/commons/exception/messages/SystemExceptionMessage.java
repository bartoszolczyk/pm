package com.example.commons.exception.messages;

import com.example.commons.exception.ErrorStub;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SystemExceptionMessage implements ErrorStub {

    PLAYER_CREATION_EXCEPTION("Player Creation Exception"),
    PLAYER_NOT_FOUND("Player Not found "),
    TEAM_NOT_FOUND("Team Not found "),
    PLAYER_UPDATE_EXCEPTION("Player Update Exception"),
    PLAYER_DELETE_EXCEPTION("Player Delete Exception"),
    PLAYER_ALREADY_TRANSFERRED("Player already transferred"),
    NO_FOUNDS_ON_TEAM_ACCOUNT("No founds on team account "),
    TRANSFER_TRANSACTION_EXCEPTION("Transfer Transaction exception");

    private final String message;

}