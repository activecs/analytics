package com.kharkiv.diploma.dto.widget;

import java.io.Serializable;

public class SalesDistribution implements Serializable {

	private static final long serialVersionUID = 5518752410511188348L;

	private Double amount;
	private Double dayNumber;

	public SalesDistribution(Double amount, Double dayNumber) {
		super();
		this.amount = amount;
		this.dayNumber = dayNumber;
	}
	
	public SalesDistribution() {
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Double dayNumber) {
		this.dayNumber = dayNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((dayNumber == null) ? 0 : dayNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesDistribution other = (SalesDistribution) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (dayNumber == null) {
			if (other.dayNumber != null)
				return false;
		} else if (!dayNumber.equals(other.dayNumber))
			return false;
		return true;
	}
	
}
