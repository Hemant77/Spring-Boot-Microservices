package com.spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.jpa.model.Customer;
import com.spring.jpa.repository.CustomerRepository;

public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public void saveCustomerDetails(Customer customer) {

		customerRepository.save(customer);

	}

}
