package com.spring.rest.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product fetchProductById(long id) {
		return productRepository.findById(id).get();
	}

	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	public Product saveProductDetails(Product product) {
		return productRepository.save(product);
	}

	public ResponseEntity<Void> entityWithLocation(Object resourceId) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/resourceId")
				.buildAndExpand(resourceId).toUri();
		return ResponseEntity.created(location).build();
	}

}
