package com.martrust.fxrates.client.impl;

import com.martrust.fxrates.client.ExchangeRateFeign;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.util.FxRatesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FxRatesClientImpl implements FxRateClient {

    @Value("${exchange.rate.io.access.key}")
    private String ACCESS_KEY_VALUE;

    private final ExchangeRateFeign exchangeRateFeign;

    @Override
    public SymbolsResponse getSymbols() {
        SymbolsResponse symbolsResponse = new SymbolsResponse();
        try {
            symbolsResponse = exchangeRateFeign.getSymbols(ACCESS_KEY_VALUE);
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
            rateResponse = exchangeRateFeign.getRate(ACCESS_KEY_VALUE, base, to);
        } catch (Exception exception) {
            FxRatesUtil.responseHandler(rateResponse);
            throw exception;
        }
        return rateResponse;
    }
}
