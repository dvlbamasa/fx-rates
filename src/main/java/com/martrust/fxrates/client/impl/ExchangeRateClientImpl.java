package com.martrust.fxrates.client.impl;

import com.martrust.fxrates.client.ExchangeRateClient;
import com.martrust.fxrates.client.ExchangeRateFeign;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeRateClientImpl implements ExchangeRateClient {

    private final ExchangeRateFeign exchangeRateFeign;

    @Override
    public SymbolsResponse getSymbols(String accessKey) {
        return exchangeRateFeign.getSymbols(accessKey);
    }

    @Override
    public RateResponse getRates(String accessKey, String base, String symbols) {
        return exchangeRateFeign.getRate(accessKey, base, symbols);
    }
}
