package com.pruhviraj.ecommerce.order_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSvcApplication.class, args);
	}

}
