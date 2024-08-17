package com.spring.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
/*
 * We can replace parameterized constructor, non-parameterized constructor,
 * getter methods, setter methods, toString method with the help of Lombok
 * annotations
 * 
 * To use these annotations we need to add Lombok dependency in pom.xml file
 */
public class Customer {
	@Id
	@Column(name = "id")
	private int customerId;

	@Column(name = "name")
	private String customerName;

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + "]";
	}

}
