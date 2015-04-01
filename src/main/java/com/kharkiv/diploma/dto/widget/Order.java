package com.kharkiv.diploma.dto.widget;

import java.util.List;

public class Order {
	
	private String id;
	private List<String> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
}
