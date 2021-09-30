package com.example.core.transfer.domain;

import com.example.commons.exception.OperationException;
import com.example.gateways.currency.CurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.util.Set;

import static com.example.commons.exception.messages.SystemExceptionMessage.NO_FOUNDS_ON_TEAM_ACCOUNT;
import static java.math.RoundingMode.HALF_EVEN;

@Component
@RequiredArgsConstructor
public class TransactionCurrencyUtils {

    private final Validator validator;
    private final CurrencyClient currencyClient;
    private static final BigDecimal TRANSFER_CONSTANT = BigDecimal.valueOf(100000).setScale(2, HALF_EVEN);

    public NativeCurrencyFee computeFeeInNativeCurrency(ComputeFeeCommand computeFeeCommand) {

        validateInput(computeFeeCommand);

        BigDecimal contractFee = getContractFee(computeFeeCommand);
        BigDecimal exchangeRate = currencyClient.getExchangeRate(computeFeeCommand.getSellerTeamCurrency(), computeFeeCommand.getBuyerTeamCurrency());
        BigDecimal nativeCurrencyFee = contractFee.multiply(exchangeRate).setScale(2, HALF_EVEN);

        if (nativeCurrencyFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new OperationException(NO_FOUNDS_ON_TEAM_ACCOUNT, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return NativeCurrencyFee.of(nativeCurrencyFee, exchangeRate);
    }

    private void validateInput(ComputeFeeCommand computeFeeCommand) {
        Set<ConstraintViolation<ComputeFeeCommand>> violations = validator.validate(computeFeeCommand);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<ComputeFeeCommand> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }

    private BigDecimal getContractFee(ComputeFeeCommand computeFeeCommand) {
        BigDecimal transferFee = getTransferFee(computeFeeCommand);
        BigDecimal teamCommission = transferFee.multiply(computeFeeCommand.getSellerTeamProvision()).setScale(2, HALF_EVEN);
        return teamCommission.add(transferFee).add(teamCommission).setScale(2, HALF_EVEN);
    }

    private BigDecimal getTransferFee(ComputeFeeCommand computeFeeCommand) {
        return BigDecimal.valueOf(computeFeeCommand.getMonthsOfExperience()).multiply(TRANSFER_CONSTANT)
            .divide(BigDecimal.valueOf(computeFeeCommand.getPlayerAge()),
                HALF_EVEN).setScale(2, HALF_EVEN);
    }

}
