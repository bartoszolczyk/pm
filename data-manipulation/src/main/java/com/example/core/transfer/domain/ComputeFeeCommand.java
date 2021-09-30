package com.example.core.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Currency;

@Builder
@Value
@AllArgsConstructor
@Valid
public class ComputeFeeCommand {

    @NotNull
    Currency sellerTeamCurrency;
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "0.10")
    BigDecimal sellerTeamProvision;
    @NotNull
    Currency buyerTeamCurrency;
    @Min(value = 0L)
    @Max(125)
    Integer playerAge;
    @PositiveOrZero
    Integer monthsOfExperience;
}
