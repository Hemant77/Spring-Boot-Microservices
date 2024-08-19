package com.spring.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.model.Product;
import com.spring.mvc.repository.ProductRepository;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable("productId") long id) {
		return productRepository.findById(id).get();
	}

	@GetMapping("/products")
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

}
