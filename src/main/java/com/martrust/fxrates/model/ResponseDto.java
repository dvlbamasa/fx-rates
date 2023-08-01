package com.martrust.fxrates.model;

import com.martrust.fxrates.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private StatusCode statusCode;
    private T data;

}
