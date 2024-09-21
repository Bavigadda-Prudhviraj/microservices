package com.pruhviraj.ecommerce.inventory_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InventorySvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySvcApplication.class, args);
	}

}
