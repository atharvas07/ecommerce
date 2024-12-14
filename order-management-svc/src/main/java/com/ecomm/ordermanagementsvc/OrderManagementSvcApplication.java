package com.ecomm.ordermanagementsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderManagementSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSvcApplication.class, args);
	}

}
