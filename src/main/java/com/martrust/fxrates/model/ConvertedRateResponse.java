package com.martrust.fxrates.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConvertedRateResponse {
    private String buy;
    private String sell;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal result;

    public static ConvertedRateResponse populate(String buy, String sell, BigDecimal amount,
                                                 BigDecimal rate, BigDecimal result) {
        return ConvertedRateResponse.builder()
                .buy(buy)
                .sell(sell)
                .amount(amount)
                .rate(rate)
                .result(result)
                .build();
    }
}
