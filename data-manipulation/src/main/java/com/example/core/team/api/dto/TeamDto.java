package com.example.core.team.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Value
@Builder
public class TeamDto {

    Long id;

    @NotBlank
    String name;

    @NotNull
    String currency;

    @NotNull
    @DecimalMin(value = "0.0")
    BigDecimal balance;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "0.10")
    BigDecimal provision;
}
