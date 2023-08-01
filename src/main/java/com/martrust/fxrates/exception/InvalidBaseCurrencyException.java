package com.martrust.fxrates.exception;

public class InvalidBaseCurrencyException extends RuntimeException {

    private String message;

    public InvalidBaseCurrencyException(String message) {
        super(message);
        this.message = message;
    }
}
