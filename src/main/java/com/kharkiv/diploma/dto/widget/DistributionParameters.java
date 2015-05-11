package com.kharkiv.diploma.dto.widget;

import java.io.Serializable;

public class DistributionParameters implements Serializable {

	private static final long serialVersionUID = 5518761410511188348L;

	private Double sigma;
	private Double dispersion;
	private Double min;
	private Double max;

	public DistributionParameters() {
		super();
	}

	public Double getSigma() {
		return sigma;
	}

	public void setSigma(Double sigma) {
		this.sigma = sigma;
	}

	public Double getDispersion() {
		return dispersion;
	}

	public void setDispersion(Double dispersion) {
		this.dispersion = dispersion;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dispersion == null) ? 0 : dispersion.hashCode());
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((sigma == null) ? 0 : sigma.hashCode());
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
		DistributionParameters other = (DistributionParameters) obj;
		if (dispersion == null) {
			if (other.dispersion != null)
				return false;
		} else if (!dispersion.equals(other.dispersion))
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (sigma == null) {
			if (other.sigma != null)
				return false;
		} else if (!sigma.equals(other.sigma))
			return false;
		return true;
	}

}
