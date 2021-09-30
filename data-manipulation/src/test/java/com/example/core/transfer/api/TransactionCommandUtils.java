package com.example.core.transfer.api;

import com.example.core.transfer.domain.ComputeFeeCommand;

import java.math.BigDecimal;
import java.util.Currency;

public interface TransactionCommandUtils {

    static ComputeFeeCommand createGoodTransaction() {

        return ComputeFeeCommand.builder()
            .buyerTeamCurrency(Currency.getInstance("EUR"))
            .playerAge(20)
            .sellerTeamProvision(BigDecimal.valueOf(0.10))
            .monthsOfExperience(30)
            .sellerTeamCurrency(Currency.getInstance("PLN"))
            .build();
    }
}
