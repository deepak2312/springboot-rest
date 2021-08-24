package com.sys.product.productservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfigLoader {
	
	@Value("${spring.kafka.broker.list}")
	private String bootstrap_servers;
	@Value("${spring.kafka.producer.inventory.topic}")
	private String inventoryTopic;
	
	public String getBootstrap_servers() {
		return bootstrap_servers;
	}
	public String getInventoryTopic() {
		return inventoryTopic;
	}
	
	
	
}
