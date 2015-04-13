package com.kharkiv.diploma.service.impl;

import static java.math.RoundingMode.HALF_UP;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.service.CurrencyService;

@Service
@Transactional(propagation=NOT_SUPPORTED)
public class CurrencyServiceImpl implements CurrencyService {

	@Override
	public BigDecimal roundCurrency(BigDecimal value) {
		return round(value);
	}

	@Override
	public double doubleValue(BigDecimal value) {
		return round(value).doubleValue();
	}

	@Override
	public String format(BigDecimal value, String format) {
		throw new RuntimeException();
	}
	
	private BigDecimal round(BigDecimal value) {
		return value.setScale(2, HALF_UP);
	}
	
}
