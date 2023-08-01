package com.martrust.fxrates.exception;

public class BaseCurrencyRestricted extends RuntimeException {

    public BaseCurrencyRestricted(String message) {
        super(message);
    }
}
