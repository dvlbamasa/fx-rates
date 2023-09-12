package com.martrust.fxrates.client;

import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;

public interface ExchangeRateClient {

    SymbolsResponse getSymbols(String accessKey);
    RateResponse getRates(String accessKey, String base, String symbols);
}
