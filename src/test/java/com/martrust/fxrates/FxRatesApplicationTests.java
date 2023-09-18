package com.martrust.fxrates;

import com.martrust.fxrates.client.FxRateClient;
import com.martrust.fxrates.exception.CurrencyDoesNotExistException;
import com.martrust.fxrates.exception.DefaultFxRateException;
import com.martrust.fxrates.model.ConvertedRateResponse;
import com.martrust.fxrates.model.RateResponse;
import com.martrust.fxrates.model.SymbolsResponse;
import com.martrust.fxrates.service.FxRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class FxRatesApplicationTests {

	@MockBean
	private FxRateClient fxRateClient;

	@MockBean
	private FxRatesService fxRatesService;

	@Test
	void assertCorrectBuyAmountRate() {
		Mockito.when(fxRateClient.fetchRate(any(String.class), any(String.class))).thenReturn(generateBuyRateResponse());
		setupMocksForSuccessTests();
		ConvertedRateResponse convertedRateResponse = fxRatesService.convert("EUR", "PHP",
				BigDecimal.valueOf(10), null);
		assertEquals(BigDecimal.valueOf(50.0), convertedRateResponse.getResult());
	}

	@Test
	void assertCorrectSellAmountRate() {
		Mockito.when(fxRateClient.fetchRate(any(String.class), any(String.class))).thenReturn(generateSellRateResponse());
		setupMocksForSuccessTests();
		ConvertedRateResponse convertedRateResponse = fxRatesService.convert("EUR", "PHP",
				null, BigDecimal.valueOf(10));
		assertEquals(BigDecimal.valueOf(30.0), convertedRateResponse.getResult());
	}

	@Test
	void assertExceptionThrownWhenNullAmounts() {
		Mockito.when(fxRateClient.fetchRate(any(String.class), any(String.class))).thenReturn(generateSellRateResponse());
		setupMocksForSuccessTests();
		assertThrows(DefaultFxRateException.class, () -> {
			fxRatesService.convert("EUR", "PHP",
					null, null);
		});
	}

	@Test
	void assertExceptionThrownWhenCurrencyDoesNotExist() {
		Mockito.when(fxRateClient.fetchRate(any(String.class), any(String.class))).thenReturn(generateSellRateResponse());
		setupMocksForSuccessTests();
		assertThrows(CurrencyDoesNotExistException.class, () -> {
			fxRatesService.convert("XXX", "PHP",
					BigDecimal.valueOf(10), null);
		});
	}
	private void setupMocksForSuccessTests() {
		Mockito.when(fxRateClient.getSymbols()).thenReturn(generateSymbolsResponse());
	}

	private RateResponse generateBuyRateResponse() {
		RateResponse rateResponse = new RateResponse();
		Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();
		rates.put("PHP", new BigDecimal("5.0"));
		rateResponse.setRates(rates);
		return rateResponse;
	}

	private RateResponse generateSellRateResponse() {
		RateResponse rateResponse = new RateResponse();
		Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();
		rates.put("EUR", new BigDecimal("3.0"));
		rateResponse.setRates(rates);
		return rateResponse;
	}

	private SymbolsResponse generateSymbolsResponse() {
		SymbolsResponse symbolsResponse = new SymbolsResponse();
		Map<String, String> symbols = new HashMap<>();
		symbols.put("EUR", "Euro");
		symbols.put("PHP", "Philippine peso");
		symbolsResponse.setSymbols(symbols);
		return symbolsResponse;
	}

	@Test
	void contextLoads() {
	}


}
