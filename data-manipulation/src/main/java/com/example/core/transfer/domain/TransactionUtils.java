package com.example.core.transfer.domain;

import com.example.commons.exception.OperationException;
import com.example.data.model.Player;
import com.example.data.model.Team;
import com.example.gateways.currency.CurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.commons.exception.messages.SystemExceptionMessage.NO_FOUNDS_ON_TEAM_ACCOUNT;
import static java.math.RoundingMode.HALF_EVEN;

@Component
@RequiredArgsConstructor
public class TransactionUtils {

    private final CurrencyClient currencyClient;
    private static final BigDecimal TRANSFER_CONSTANT = BigDecimal.valueOf(100000).setScale(2, HALF_EVEN);

    public NativeCurrencyFee computeFeeInNativeCurrency(Team sellerTeam, Team buyerTeam, Player player) {

        BigDecimal contractFee = getContractFee(sellerTeam, player);
        BigDecimal exchangeRate = currencyClient.getExchangeRate(sellerTeam.getCurrency(), buyerTeam.getCurrency());
        BigDecimal nativeCurrencyFee = contractFee.multiply(exchangeRate).setScale(2, HALF_EVEN);

        if (nativeCurrencyFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new OperationException(NO_FOUNDS_ON_TEAM_ACCOUNT, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return NativeCurrencyFee.of(nativeCurrencyFee, exchangeRate);
    }

    private BigDecimal getContractFee(Team owningTeam, Player player) {
        BigDecimal transferFee = getTransferFee(player);
        BigDecimal teamCommission = transferFee.multiply(owningTeam.getProvision()).setScale(2, HALF_EVEN);
        return teamCommission.add(transferFee).add(teamCommission).setScale(2, HALF_EVEN);
    }

    private BigDecimal getTransferFee(Player player) {
        return BigDecimal.valueOf(player.getMonthsOfExperience()).multiply(TRANSFER_CONSTANT).divide(BigDecimal.valueOf(player.getAge()),
            HALF_EVEN).setScale(2, HALF_EVEN);
    }



}
