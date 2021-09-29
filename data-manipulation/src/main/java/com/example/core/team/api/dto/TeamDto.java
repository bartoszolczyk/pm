package com.example.core.team.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @NotNull
    BigDecimal balance;

    @NotNull
    @Positive
    BigDecimal provision;
}
