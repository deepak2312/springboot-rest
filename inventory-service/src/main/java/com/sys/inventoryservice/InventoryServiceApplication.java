package com.sys.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;

import com.sys.product.productservice.beans.ProductEvent;

@SpringBootApplication
@ComponentScan("com.sys.inventoryservice.*")
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	

	
}
