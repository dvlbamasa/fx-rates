package com.martrust.fxrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FxRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxRatesApplication.class, args);
	}

}
