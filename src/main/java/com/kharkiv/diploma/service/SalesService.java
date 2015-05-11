package com.kharkiv.diploma.service;

import java.util.Date;
import java.util.List;

import com.kharkiv.diploma.dto.widget.DistributionParameters;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.dto.widget.SalesApproximation;
import com.kharkiv.diploma.dto.widget.SalesDistribution;
import com.kharkiv.diploma.dto.widget.SalesForDay;

public interface SalesService {
	
	List<SalesForDay> getSales(Date startDate, Date endDate, Product product);
	
	List<SalesDistribution> getDistribution(Date startDate, Date endDate, Product product);
	
	List<SalesApproximation> getApproximatedDistribution(Date startDate, Date endDate, Product product);
	
	DistributionParameters getDistributionParameters(Date startDate, Date endDate, Product product);
	
}
