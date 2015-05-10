package com.kharkiv.diploma.service;

import java.util.Date;
import java.util.List;

import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.dto.widget.SalesForDay;

public interface SalesService {
	
	List<SalesForDay> getSales(Date startDate, Date endDate, Product product);
	
}
