package com.martrust.fxrates.controller;

import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.enums.StatusCode;
import com.martrust.fxrates.model.ConvertedRateResponse;
import com.martrust.fxrates.model.ResponseDto;
import com.martrust.fxrates.service.FxRatesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/fx-rates")
public class FxRatesController {

    private FxRateClient client;
    private FxRatesService fxRatesService;

    @GetMapping("/symbols")
    private ResponseEntity<ResponseDto<Map<String, String>>> getSymbols() {
        return ResponseEntity.ok(
                ResponseDto.<Map<String,String>>builder()
                        .data(client.getSymbols().getSymbols())
                        .statusCode(StatusCode.SUCCESS)
                        .build()
        );
    }

    @GetMapping("/convert")
    private ResponseEntity<ResponseDto<ConvertedRateResponse>> convert(@RequestParam("buy") String buy,
                                                                       @RequestParam("sell") String sell,
                                                                       @RequestParam(value = "buyAmount", required = false) BigDecimal buyAmount,
                                                                       @RequestParam(value = "sellAmount", required = false) BigDecimal sellAmount) {
        return ResponseEntity.ok(
                ResponseDto.<ConvertedRateResponse>builder()
                        .data(fxRatesService.convert(buy, sell, buyAmount, sellAmount))
                        .statusCode(StatusCode.SUCCESS)
                        .build()
        );
    }

}
