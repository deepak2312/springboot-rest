package com.sys.product.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sys.product.productservice.beans.Product;
import com.sys.product.productservice.beans.ProductEvent;
import com.sys.product.productservice.configs.CustomConfigLoader;
import com.sys.product.productservice.services.ProductService;

@RestController
@RequestMapping(value="/api/product")
public class Controller{
	
	@Autowired
	public ProductService productService;
	
	@Autowired
	KafkaTemplate<String, ProductEvent> kafkaTemplate;
	
	@Autowired
	CustomConfigLoader configLoader;
	
	@GetMapping(value="/getProductList",produces="application/json")
	public ResponseEntity<List<Product>> getProductList() {
		System.out.println("Request Landed on this instance ");
		List<Product> products=productService.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping(value="/addProduct",produces="application/json",consumes="application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		Product productRes=productService.addProduct(product);
		if(productRes!=null) {
			ProductEvent event=new ProductEvent(productRes.getProductId(), 0);
			kafkaTemplate.send(configLoader.getInventoryTopic(), event);
		}
		return productRes!=null?new ResponseEntity<>(product,HttpStatus.CREATED)
									  :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}