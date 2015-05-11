package com.kharkiv.diploma.dto.widget;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kharkiv.diploma.controller.form.MorrisDateSerializer;
import com.kharkiv.diploma.controller.form.MorrisDateDeserializer;

public class SalesForDay implements Serializable {

	private static final long serialVersionUID = 5518752410511188348L;

	@JsonDeserialize(using = MorrisDateDeserializer.class)
	@JsonSerialize(using = MorrisDateSerializer.class)
	private Date date;
	private Integer amount;
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
