package com.martrust.fxrates.client;

import com.martrust.fxrates.config.FeignClientConfiguration;
import com.martrust.fxrates.exception.FeignClientApiException;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="fx-rates-client",
        url = "http://api.exchangeratesapi.io/v1",
        configuration = FeignClientConfiguration.class,
        fallback = ExchangeRateFeign.ExchangeRateClientFallback.class)
public interface ExchangeRateFeign {

    @GetMapping("/symbols")
    SymbolsResponse getSymbols(@RequestParam("access_key") String accessKey);

    @GetMapping("/latest")
    RateResponse getRate(@RequestParam("access_key") String accessKey, @RequestParam("base") String base,
                         @RequestParam("symbols") String symbols);

    class ExchangeRateClientFallback implements ExchangeRateFeign {

        @Override
        public SymbolsResponse getSymbols(String accessKey) {
            throw new FeignClientApiException("Exception occurred after accessing symbols API");
        }

        @Override
        public RateResponse getRate(String accessKey, String base, String symbols) {
            throw new FeignClientApiException("Exception occurred after accessing rate API");
        }
    }
}
