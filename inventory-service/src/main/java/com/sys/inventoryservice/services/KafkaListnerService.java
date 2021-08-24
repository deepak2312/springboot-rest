package com.sys.inventoryservice.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sys.inventoryservice.entity.InventoryDetail;
import com.sys.inventoryservice.entity.InventoryDetailEntity;
import com.sys.product.productservice.beans.ProductEvent;

@Service
@EnableKafka
public class KafkaListnerService {
	
	@Autowired
	@Qualifier("usableInventoryRepository")
	CrudRepository<InventoryDetailEntity, Long> inventoryRepo;
	
	@KafkaListener(
		    topics = "inventory_topic",
		    groupId="reflectoring-user",
		    containerFactory="userKafkaListenerContainerFactory")
 	 void listener(ProductEvent productEvent) {
	    System.out.println("CustomUserListener [{}]"+ productEvent);
	    if(productEvent!=null) {
	    	InventoryDetailEntity inventoryDetail=new InventoryDetailEntity(
	    														Long.valueOf(productEvent.getProductId()),
	    														productEvent.getQuantity(),
	    														LocalDate.now(),
	    														"admin");
	    	saveNewProduct(inventoryDetail);
	    }
	 }
	
	 void saveNewProduct(InventoryDetailEntity inventoryDetail) {
		 inventoryRepo.save(inventoryDetail);
	 }
	
}
