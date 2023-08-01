package com.martrust.fxrates.exception;

import com.martrust.fxrates.client.impl.FxRatesClientImpl;
import com.martrust.fxrates.model.BaseResponse;
import com.martrust.fxrates.util.FxRatesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.ObjectInputStream;


@Component
public class ResponseHandler implements ResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            throw new FxRateAPIException("Bad Request. Make sure to only use EUR as base currency since this is a limited subscription.");
        }
    }
}
