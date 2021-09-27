package com.example.core.player.team.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Year;

@Value
@Builder
public class TeamDto {

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
