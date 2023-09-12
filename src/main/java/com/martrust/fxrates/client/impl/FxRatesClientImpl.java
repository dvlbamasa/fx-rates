package com.martrust.fxrates.client.impl;

import com.martrust.fxrates.client.ExchangeRateClient;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.util.FxRatesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FxRatesClientImpl implements FxRateClient {

    @Value("${exchange.rate.io.access.key}")
    private String ACCESS_KEY_VALUE;

    private final ExchangeRateClient exchangeRateClient;

    public FxRatesClientImpl(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    @Override
    public SymbolsResponse getSymbols() {
        SymbolsResponse symbolsResponse = new SymbolsResponse();
        try {
            symbolsResponse = exchangeRateClient.getSymbols(ACCESS_KEY_VALUE);
        } catch (Exception exception) {
            FxRatesUtil.responseHandler(symbolsResponse);
            throw exception;
        }
        return symbolsResponse;
    }

    @Override
    public RateResponse fetchRate(String base, String to) {
        RateResponse rateResponse = new RateResponse();
        try {
            rateResponse = exchangeRateClient.getRates(ACCESS_KEY_VALUE, base, to);
        } catch (Exception exception) {
            FxRatesUtil.responseHandler(rateResponse);
            throw exception;
        }
        return rateResponse;
    }
}
