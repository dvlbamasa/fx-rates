package com.martrust.fxrates.util;

import com.martrust.fxrates.exception.BaseCurrencyRestricted;
import com.martrust.fxrates.exception.DefaultFxRateException;
import com.martrust.fxrates.exception.InvalidBaseCurrencyException;
import com.martrust.fxrates.exception.ResponseHandler;
import com.martrust.fxrates.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FxRatesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxRatesUtil.class);

    public static void responseHandler(BaseResponse baseResponse) {
        LOGGER.info("{}" + baseResponse);
        if (baseResponse.getError() != null) {
            switch (baseResponse.getError().getCode()) {
                case "invalid_base_currency" -> throw new InvalidBaseCurrencyException("Invalid base currency!");
                case "base_currency_access_restricted" ->
                        throw new BaseCurrencyRestricted("Only EUR is allowed since this is a limited subscription");
                default ->
                        throw new DefaultFxRateException("Exception occurred during API call: " + baseResponse.getError().getMessage());
            }
        }
    }
}
