package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
