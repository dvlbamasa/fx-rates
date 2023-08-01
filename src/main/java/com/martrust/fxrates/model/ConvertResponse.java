package com.martrust.fxrates.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConvertResponse extends BaseResponse {

    private QueryData query;
    private InfoData info;
    private String historical;
    private LocalDate date;
    private BigDecimal result;
    @Data
    public class QueryData {
        private String from;
        private String to;
        private BigDecimal amount;
    }
    @Data
    public class InfoData {
        private String timestamp;
        private BigDecimal rate;
    }
}
