package com.martrust.fxrates.exception;

import com.martrust.fxrates.enums.StatusCode;
import com.martrust.fxrates.model.ResponseDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

    @ExceptionHandler(CurrencyDoesNotExistException.class)
    public ResponseEntity<ResponseDto<String>> handle(CurrencyDoesNotExistException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(
                        ResponseDto.<String>builder()
                                .statusCode(StatusCode.FAILED)
                                .data(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(FxRateAPIException.class)
    public ResponseEntity<ResponseDto<String>> handle(FxRateAPIException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(
                        ResponseDto.<String>builder()
                                .statusCode(StatusCode.FAILED)
                                .data(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(DefaultFxRateException.class)
    public ResponseEntity<ResponseDto<String>> handle(DefaultFxRateException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(
                        ResponseDto.<String>builder()
                                .statusCode(StatusCode.FAILED)
                                .data(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(InvalidBaseCurrencyException.class)
    public ResponseEntity<ResponseDto<String>> handle(InvalidBaseCurrencyException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(
                        ResponseDto.<String>builder()
                                .statusCode(StatusCode.FAILED)
                                .data(exception.getMessage())
                                .build()
                );
    }

}
