package com.sys.inventoryservice.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.sys.inventoryservice.entity.InventoryDetailEntity;
import com.sys.inventoryservice.requests.InventoryResponse;

@Service
public class InventoryService {

	@Autowired
	@Qualifier("usableInventoryRepository")
	CrudRepository<InventoryDetailEntity, Long> inventoryRepo;
	
	@Transactional
	public InventoryResponse updateProductInventory(InventoryDetailEntity inventoryDetailEntity) {
		InventoryDetailEntity response=inventoryRepo.findById(inventoryDetailEntity.getProductId()).orElseGet(()->new InventoryDetailEntity());
		if(response.getProductId()!=-1) {
			InventoryDetailEntity updateRes=inventoryRepo.save(inventoryDetailEntity);
			if(updateRes!=null) {
				return new InventoryResponse(updateRes.getProductId(), updateRes.getQuantity(), updateRes.getAdditionDate(), updateRes.getUpdatedBy());
			}
		}
		return null;
	}
}
