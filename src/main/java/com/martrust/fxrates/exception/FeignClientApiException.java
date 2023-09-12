package com.martrust.fxrates.exception;

public class FeignClientApiException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Client not working!";

    public FeignClientApiException() {
        super(DEFAULT_MESSAGE);
    }

    public FeignClientApiException(String message) {
        super(message);
    }
}
