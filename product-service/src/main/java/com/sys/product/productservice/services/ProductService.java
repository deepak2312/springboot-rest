package com.sys.product.productservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.product.productservice.beans.Product;
import com.sys.product.productservice.repos.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository ;
	
	@Transactional
	public List<Product> getProducts() {
		 return  (List<Product>) productRepository.findAll();
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
}
