package com.martrust.fxrates.service.impl;

import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.exception.CurrencyDoesNotExistException;
import com.martrust.fxrates.exception.DefaultFxRateException;
import com.martrust.fxrates.model.ConvertedRateResponse;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import com.martrust.fxrates.service.FxRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


@Service
public class FxRatesServiceImpl implements FxRatesService {

    @Autowired
    private FxRateClient fxRateClient;

    @Override
    public ConvertedRateResponse convert(String buy, String sell, BigDecimal buyAmount, BigDecimal sellAmount) {
        validateCurrency(buy);
        validateCurrency(sell);

        BigDecimal baseAmount;
        String convertedCurrency;
        RateResponse rateResponse;
        if (buyAmount != null) {
            rateResponse = fxRateClient.fetchRate(buy, sell);
            baseAmount = buyAmount;
            convertedCurrency = sell;
        } else if (sellAmount != null){
            rateResponse = fxRateClient.fetchRate(sell, buy);
            baseAmount = sellAmount;
            convertedCurrency = buy;
        } else {
            throw new DefaultFxRateException("Please input either buy or sell amount!");
        }

        BigDecimal rate = rateResponse.getRates().get(convertedCurrency);
        BigDecimal result = baseAmount.multiply(rate);

        return ConvertedRateResponse.populate(buy, sell, baseAmount, rate, result);
    }

    private void validateCurrency(String currency) {
        SymbolsResponse symbolsResponse = fxRateClient.getSymbols();
        Map<String, String> symbols = symbolsResponse.getSymbols();
        if (!symbols.containsKey(currency)) {
            throw new CurrencyDoesNotExistException("Currency: " + currency + " does not exist.");
        }
    }
}
