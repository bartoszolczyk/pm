package com.example.core.player.api.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class PlayerDto {

    @NotNull
    Long id;

    @Positive
    @NotNull
    Long age;

    @NotNull
    String name;

    @NotNull
    String surname;

    String team;

}
