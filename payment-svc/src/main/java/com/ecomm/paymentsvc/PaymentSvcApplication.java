package com.ecomm.paymentsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentSvcApplication.class, args);
	}

}
