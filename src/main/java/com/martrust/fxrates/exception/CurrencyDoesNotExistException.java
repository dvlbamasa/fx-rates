package com.martrust.fxrates.exception;

public class CurrencyDoesNotExistException extends RuntimeException {

    private String message;
    public CurrencyDoesNotExistException (String message) {
        super(message);
        this.message = message;
    }
}
