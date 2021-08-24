package com.sys.inventoryservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.sys.product.productservice.beans.ProductEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Autowired
	private CustomConfigLoader customConfigLoader;

	public ConsumerFactory<String, ProductEvent> userConsumerFactory() {
		
		JsonDeserializer<ProductEvent> deserializer = new JsonDeserializer<>(ProductEvent.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
		
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "reflectoring-user");
	    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

	    return new DefaultKafkaConsumerFactory<>(props,
	      new StringDeserializer(),
	      new JsonDeserializer<>(ProductEvent.class));
	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, ProductEvent> userKafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, ProductEvent> factory =
	      new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(userConsumerFactory());
	    return factory;
	  }
}
