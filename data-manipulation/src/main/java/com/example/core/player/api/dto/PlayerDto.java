package com.example.core.player.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
@Builder
public class PlayerDto {

    @NotNull
    Long id;

    @Positive
    @NotNull
    Integer age;

    @NotNull
    String name;

    @NotNull
    String surname;

    String team;

}
