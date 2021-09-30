package com.example.core.transfer.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.transfer.domain.ComputeFeeCommand;
import com.example.core.transfer.domain.NativeCurrencyFee;
import com.example.core.transfer.domain.TransactionCurrencyUtils;
import com.example.gateways.currency.CurrencyClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TransactionUtilsTest extends IntegrationTestConfig implements TransactionCommandUtils {

    @MockBean
    CurrencyClient currencyClient;

    @Autowired
    TransactionCurrencyUtils transactionCurrencyUtils;

    @Test
    void positiveOutcome() {
        when(currencyClient.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.valueOf(4.61));

        NativeCurrencyFee out = transactionCurrencyUtils.computeFeeInNativeCurrency(TransactionCommandUtils.createGoodTransaction());

        assertEquals(BigDecimal.valueOf(829800.00).setScale(2, RoundingMode.HALF_EVEN), out.getNativeCurrencyFee());
        assertEquals(BigDecimal.valueOf(4.61), out.getExchangeRate());
    }

    @Test
    void checkCommanderTeamCurrency() {

        assertThrows(IllegalArgumentException.class, () -> {
            ComputeFeeCommand.builder()
                .buyerTeamCurrency(Currency.getInstance("EXX"))
                .playerAge(20)
                .sellerTeamProvision(BigDecimal.valueOf(0.10))
                .monthsOfExperience(30)
                .sellerTeamCurrency(Currency.getInstance("PLN"))
                .build();
        });

    }

    @Test
    void checkCommandPlayerAge() {

        ComputeFeeCommand input = ComputeFeeCommand.builder()
            .buyerTeamCurrency(Currency.getInstance("EUR"))
            .playerAge(-100)
            .sellerTeamProvision(BigDecimal.valueOf(0.10))
            .monthsOfExperience(30)
            .sellerTeamCurrency(Currency.getInstance("PLN"))
            .build();

        assertThrows(ConstraintViolationException.class, () -> transactionCurrencyUtils.computeFeeInNativeCurrency(input));
    }

    @Test
    void checkCommandeerTeamProvision() {

        ComputeFeeCommand input = ComputeFeeCommand.builder()
            .buyerTeamCurrency(Currency.getInstance("EUR"))
            .playerAge(20)
            .sellerTeamProvision(BigDecimal.valueOf(0.49))
            .monthsOfExperience(30)
            .sellerTeamCurrency(Currency.getInstance("PLN"))
            .build();
        assertThrows(ConstraintViolationException.class, () -> transactionCurrencyUtils.computeFeeInNativeCurrency(input));

    }

    @Test
    void checkCommandMonthsOfExperience() {
        ComputeFeeCommand input = ComputeFeeCommand.builder()
            .buyerTeamCurrency(Currency.getInstance("EUR"))
            .playerAge(50)
            .sellerTeamProvision(BigDecimal.valueOf(0.10))
            .monthsOfExperience(-1000)
            .sellerTeamCurrency(Currency.getInstance("PLN"))
            .build();
        assertThrows(ConstraintViolationException.class, () -> transactionCurrencyUtils.computeFeeInNativeCurrency(input));

    }

    @Test
    void checkCommandSellerTeamCurrency() {
        assertThrows(IllegalArgumentException.class, () -> {
            ComputeFeeCommand.builder()
                .buyerTeamCurrency(Currency.getInstance("EUR"))
                .playerAge(20)
                .sellerTeamProvision(BigDecimal.valueOf(0.10))
                .monthsOfExperience(30)
                .sellerTeamCurrency(Currency.getInstance("PUZ"))
                .build();
        });

    }

}