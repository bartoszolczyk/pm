package com.example.core.transaction.api.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
@Builder
public class TransactionDto {

    @Positive
    @NotNull
    Long originTeamiD;
    @Positive
    @NotNull
    Long destinationTeamId;
    @Positive
    @NotNull
    Long playerId;

}
