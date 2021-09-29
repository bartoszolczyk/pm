package com.example.core.transfer.domain;

import lombok.Builder;

@Builder
public class PlayerTransactionDetails {

    private final Integer age;
    private final Integer monthsOfExperience;
    private final Double transferFee ;
}
