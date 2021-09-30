package com.example.core.transfer.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class NativeCurrencyFee {

    BigDecimal nativeCurrencyFee;
    BigDecimal exchangeRate;

}