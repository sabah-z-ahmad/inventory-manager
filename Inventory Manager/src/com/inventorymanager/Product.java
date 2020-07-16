package com.inventorymanager;

import java.sql.Date;

public class Product {
	int productId;
	int categoryId;
	int brandId;
	int quantity;
	float cost;
	String name;
	Date expiration;
	
	
	public Product(int productId, int categoryId, int brandId, String name, int quantity, float cost, Date expiration)	{
		this.productId = productId;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.quantity = quantity;
		this.cost = cost;
		this.name = name;
		this.expiration = expiration;
	}

	public Object[] getObjectArray()	{
		Object[] properties = {productId, categoryId, brandId, name, quantity, cost, expiration};
		return properties;
	}

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getBrandId() {
		return brandId;
	}


	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}


	
		
}
