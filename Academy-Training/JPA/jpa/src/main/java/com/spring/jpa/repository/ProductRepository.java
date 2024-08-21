package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//	public Product findById(Long id);

}
