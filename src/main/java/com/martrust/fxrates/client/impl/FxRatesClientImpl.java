package com.martrust.fxrates.client.impl;

import com.martrust.fxrates.exception.ResponseHandler;
import com.martrust.fxrates.model.ConvertResponse;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.util.FxRatesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class FxRatesClientImpl implements FxRateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxRatesClientImpl.class);

    private static final String RATE_PROVIDER_URL = "http://api.exchangeratesapi.io/v1/";
    private static final String SYMBOLS_URI = "symbols";
    private static final String RATES_URI = "latest";
    private static final String CONVERT_URI = "convert";
    private static final String ACCESS_KEY = "?access_key=dd89c1b60e3fae99846ced12a197db67";

    private RestTemplate restTemplate;

    @Autowired
    public FxRatesClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new ResponseHandler())
                .build();
    }
    @Override
    public SymbolsResponse getSymbols() {
        StringBuilder uri = new StringBuilder(RATE_PROVIDER_URL)
                .append(SYMBOLS_URI)
                .append(ACCESS_KEY);
        SymbolsResponse symbolsResponse = new SymbolsResponse();
        try {
            symbolsResponse = restTemplate.getForObject(uri.toString(), SymbolsResponse.class);
        } catch (Exception exception) {
            FxRatesUtil.responseHandler(symbolsResponse);
            throw exception;
        }
        return symbolsResponse;
    }

    @Override
    public RateResponse fetchRate(String base, String to) {
        StringBuilder uri = new StringBuilder(RATE_PROVIDER_URL)
                .append(RATES_URI)
                .append(ACCESS_KEY)
                .append("&base=")
                .append(base)
                .append("&symbols=")
                .append(to);
        RateResponse rateResponse = new RateResponse();
        try {
            rateResponse = restTemplate.getForObject(uri.toString(), RateResponse.class);
        } catch (Exception exception) {
            FxRatesUtil.responseHandler(rateResponse);
            throw exception;
        }
        return rateResponse;
    }

    @Override
    public ConvertResponse convert(String from, String to, BigDecimal amount) {
        StringBuilder uri = new StringBuilder(RATE_PROVIDER_URL)
                .append(CONVERT_URI)
                .append(ACCESS_KEY)
                .append("&from=")
                .append(from)
                .append("&to=")
                .append(to)
                .append("&amount=")
                .append(amount);
        ConvertResponse convertResponse = restTemplate.getForObject(uri.toString(), ConvertResponse.class);
        return convertResponse;
    }


}
