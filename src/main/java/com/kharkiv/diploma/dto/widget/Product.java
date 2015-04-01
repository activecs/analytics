package com.kharkiv.diploma.dto.widget;

import java.math.BigDecimal;

public class Product {
	
	private String name;
	private String SKU;
	private BigDecimal price;
	private String category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String SKU) {
		this.SKU = SKU;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
