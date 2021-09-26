package com.example.core.player.team.api.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Value
public class TeamDto {

    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotNull
    LocalDate foundation;

    @NotBlank
    String location;

    @NotNull
    String currency;
}
