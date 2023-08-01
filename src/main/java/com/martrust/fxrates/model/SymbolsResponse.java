package com.martrust.fxrates.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class SymbolsResponse extends BaseResponse {
    Map<String, String> symbols;
}
