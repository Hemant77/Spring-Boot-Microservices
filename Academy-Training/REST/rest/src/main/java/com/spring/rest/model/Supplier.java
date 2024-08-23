package com.spring.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {

	@Id
	private long supplierId;

	private String supplierName;

	private String productName;

	public Supplier() {
		super();
	}

	public Supplier(long supplierId, String supplierName, String productName) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.productName = productName;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", productName=" + productName
				+ "]";
	}

}
