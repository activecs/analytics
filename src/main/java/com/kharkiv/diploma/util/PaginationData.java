package com.kharkiv.diploma.util;

import com.kharkiv.diploma.dto.BaseEntity.SortOrder;

public class PaginationData {
	
	private String sortBy;
	private SortOrder sortOrder;
	private Integer pageSize;
	
	public String getSortBy() {
		return sortBy;
	}
	
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
