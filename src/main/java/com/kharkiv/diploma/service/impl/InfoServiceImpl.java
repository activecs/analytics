package com.kharkiv.diploma.service.impl;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.dto.widget.Info;
import com.kharkiv.diploma.service.CurrencyService;
import com.kharkiv.diploma.service.InfoService;
import com.kharkiv.diploma.service.PageViewService;
import com.kharkiv.diploma.service.SessionService;
import com.kharkiv.diploma.service.TransactionService;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
	
	@Inject
	private TransactionService transactionService;
	@Inject
	private CurrencyService currencyService;
	@Inject
	private PageViewService pageViewService;
	@Inject
	private SessionService sessionService;
	
	@Override
	public Info getInfo() {
		Info info = new Info();
		BigDecimal totalRevenue = transactionService.getTotalRevenue();
		info.setRevenue(currencyService.doubleValue(totalRevenue));
		info.setSales(transactionService.getAll().size());
		info.setVisits(sessionService.getAll().size());
		info.setPageViews(pageViewService.getAll().size());
		return info;
	}
	
	
}
