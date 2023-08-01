package com.martrust.fxrates.exception;

public class DefaultFxRateException extends RuntimeException{

    private String message;

    public DefaultFxRateException(String message) {
        super(message);
        this.message = message;
    }
}
