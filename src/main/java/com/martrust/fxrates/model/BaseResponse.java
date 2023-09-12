package com.martrust.fxrates.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseResponse {

    private Boolean success;
    private Long timestamp;
    private String base;
    private LocalDate date;
    private ErrorData error;

    @Data
    public static class ErrorData {
        private String code;
        private String message;
    }
}
