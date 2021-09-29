package com.example.gateways.currency;

import com.example.commons.exception.OperationException;
import com.example.commons.exception.messages.SystemExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    private static final String CURRENCY_API = "https://api.frankfurter.app/latest?from=";

    public BigDecimal getExchangeRate(Currency owningCurrency, Currency clientCurrency) {
        if (owningCurrency.equals(clientCurrency)) {
            return BigDecimal.valueOf(1);
        }
        CurrencyResponse currencyResponse;
        BigDecimal result;
        try {
            RestTemplate template = new RestTemplate();

            currencyResponse = template.getForObject(getUrl(owningCurrency),
                CurrencyResponse.class);

            Map<String, Double> codes = Optional.ofNullable(currencyResponse)
                .map(CurrencyResponse::getRates)
                .orElseThrow(() -> new RestClientException(" invalid "
                    + "payload "));

            result = getExchange(clientCurrency, codes);

        } catch (RestClientException e) {
            e.printStackTrace();
            throw new OperationException(SystemExceptionMessage.PLAYER_CREATION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return result;
    }

    private BigDecimal getExchange(Currency clientCurrency, Map<String, Double> codes) {
        BigDecimal result;
        if (codes.containsKey(clientCurrency.getCurrencyCode())) {
            result = BigDecimal.valueOf(codes.get(clientCurrency.getCurrencyCode())).setScale(2, RoundingMode.HALF_EVEN);

        } else {
            throw new OperationException(SystemExceptionMessage.PLAYER_CREATION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private String getUrl(Currency owningCurrency) {
        return CURRENCY_API  + owningCurrency.getCurrencyCode();
    }
}
