package com.spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
