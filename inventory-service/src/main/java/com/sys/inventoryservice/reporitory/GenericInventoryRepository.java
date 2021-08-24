package com.sys.inventoryservice.reporitory;

import org.springframework.data.repository.CrudRepository;

import com.sys.inventoryservice.entity.InventoryDetailEntity;


public interface GenericInventoryRepository extends CrudRepository<InventoryDetailEntity, Long>{

}
