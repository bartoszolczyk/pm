package com.example.core.transfer.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class TransferTransactionDto {

    Long id;
    Long playerId;
    Long sellerId;
    Long buyerID;
    String buyerCurrency;
    String sellerCurrency;
    BigDecimal amount;
    BigDecimal exchangeRate;

}
