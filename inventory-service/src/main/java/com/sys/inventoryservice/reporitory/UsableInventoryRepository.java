package com.sys.inventoryservice.reporitory;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sys.inventoryservice.entity.InventoryDetail;
import com.sys.inventoryservice.entity.InventoryDetailEntity;

@Repository
public interface UsableInventoryRepository extends GenericInventoryRepository {
	@SuppressWarnings("unchecked")
	public InventoryDetailEntity save(InventoryDetailEntity InventoryDetailEntity);
	
	Optional<InventoryDetailEntity> findById(Long productId);
	
}
