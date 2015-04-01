package com.kharkiv.diploma.dto.widget;

import java.util.Objects;

public class Browser {
	
	private String name;
	
	public Browser() {
	}
	
	public Browser(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Browser))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		return Objects.equals(name, ((Browser) obj).name);
	}
	
}
