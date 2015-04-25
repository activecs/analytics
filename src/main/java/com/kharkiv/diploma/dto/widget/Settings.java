package com.kharkiv.diploma.dto.widget;

import java.io.Serializable;
import java.util.Date;

public class Settings implements Serializable {
	
	private static final long serialVersionUID = 5518752410511188348L;
	
	private Product product;
	private Date from;
	private Date to;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

}
