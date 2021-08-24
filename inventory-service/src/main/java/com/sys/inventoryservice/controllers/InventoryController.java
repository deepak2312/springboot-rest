package com.sys.inventoryservice.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sys.inventoryservice.entity.InventoryDetailEntity;
import com.sys.inventoryservice.requests.InventoryRequest;
import com.sys.inventoryservice.requests.InventoryResponse;
import com.sys.inventoryservice.services.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@PatchMapping(path="/updateInventory/{id}",consumes="application/json-patch+json")
	public ResponseEntity<InventoryResponse> updateInventoryQuantity(@PathVariable int id,@RequestBody InventoryRequest inventoryRequest) {
		System.out.println("Request Detail is : "+id+" ["+inventoryRequest.toString()+"]");
		InventoryDetailEntity detailEntity = new InventoryDetailEntity(inventoryRequest.getProductId(),
				inventoryRequest.getQuantity(),inventoryRequest.getAdditionDate(),
				inventoryRequest.getUpdateBy());
		InventoryResponse response = inventoryService.updateProductInventory(detailEntity);
		if (response != null) {
			return new ResponseEntity<InventoryResponse>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<InventoryResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> get() {
		System.out.println("Request Detail is");
		
			return new ResponseEntity<String>("Hello",HttpStatus.OK);
		
	}
}
