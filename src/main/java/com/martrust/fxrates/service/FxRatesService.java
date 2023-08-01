package com.martrust.fxrates.service;

import com.martrust.fxrates.model.ConvertedRateResponse;

import java.math.BigDecimal;

public interface FxRatesService {
    ConvertedRateResponse convert(String buy, String from, BigDecimal buyAmount, BigDecimal sellAmount);
}
