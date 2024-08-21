package com.spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.spring.jpa.model.Product;
import com.spring.jpa.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product fetchProductById(int id) {
		return productRepository.findById(id).get();
	}

	public Product saveProductDeatils(Product product) {
		return productRepository.save(product);
	}

	@Query("insert into customer(id,name) values(1, ?1)")
	public void saveCustomerDetails(String name) {

	}
}
