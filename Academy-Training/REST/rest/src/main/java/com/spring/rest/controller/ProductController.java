package com.spring.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;

@RestController
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

//	@Autowired
//	private ProductRepository productRepository;

	@GetMapping(value = "/products")
	public List<Product> getAllProducts() {
		logger.info("Fetch all products...");
		return productService.fetchAllProducts();
	}

	@GetMapping(value = "/products/{id}")
	public Product getProductById(@PathVariable Long id) {
		logger.info("Fetch product by id...");
		return productService.fetchProductById(id);
	}

	@PostMapping("/products")
	public ResponseEntity<Void> creatProduct(@RequestBody Product product) {
		logger.info("Create a new product... " + product.toString());
		productService.saveProductDetails(product);
		return productService.entityWithLocation(product.getId());
	}

}
