package com.martrust.fxrates.client;

import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;


public interface FxRateClient {

    SymbolsResponse getSymbols();

    RateResponse fetchRate(String base, String to);
}
