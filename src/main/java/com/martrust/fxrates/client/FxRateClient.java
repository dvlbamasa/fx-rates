package com.martrust.fxrates.client;

import com.martrust.fxrates.model.ConvertResponse;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;

import java.math.BigDecimal;
import java.util.Map;

public interface FxRateClient {

    SymbolsResponse getSymbols();

    RateResponse fetchRate(String base, String to);

    ConvertResponse convert(String from, String to, BigDecimal amount);
}
