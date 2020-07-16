package com.inventorymanager;

public class Category {
	private int categoryId;
	private String name;
	
	public Category(int id, String name)	{
		this.categoryId = id;
		this.name = name;
	}

	public Object[] getObjectArray() {
		Object[] properties = {categoryId, name};
		return properties;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int id) {
		this.categoryId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
