package com.kharkiv.diploma.service;

import java.math.BigDecimal;

public interface CurrencyService {
	
	BigDecimal roundCurrency(BigDecimal value);
	
	double doubleValue(BigDecimal value);
	
	String format(BigDecimal value, String format);
	
}
