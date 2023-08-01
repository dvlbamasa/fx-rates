package com.martrust.fxrates.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class RateResponse extends BaseResponse{
    Map<String, BigDecimal> rates;
}
