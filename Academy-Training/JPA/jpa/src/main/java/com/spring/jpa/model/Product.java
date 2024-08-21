package com.spring.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
//	@Column is only required if we are using the column name(i.e. id) and variable name(i.e. productId)
	private Long productId;

//	@Column(name = "name")
//	@Column is not required if we are using the same column name and variable name
	private String name;

	@Column(name = "price")
	private float price;

	public Product() {
		super();
	}

	public Product(Long productId, String name, float price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + "]";
	}

}
