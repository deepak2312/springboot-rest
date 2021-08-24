package com.sys.product.productservice.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sys.product.productservice.beans.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
