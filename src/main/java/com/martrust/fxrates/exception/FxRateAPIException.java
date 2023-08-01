package com.martrust.fxrates.exception;

public class FxRateAPIException extends RuntimeException{
    private String message;

    public FxRateAPIException(String message) {
        super(message);
        this.message = message;
    }
}
