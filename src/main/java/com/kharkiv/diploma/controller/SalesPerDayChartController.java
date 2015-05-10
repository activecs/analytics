package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.SalesForDay;
import com.kharkiv.diploma.dto.widget.Settings;
import com.kharkiv.diploma.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesPerDayChartController {
	
	private static final String SETTINGS_PROPERTY = "settings";
	@Inject
	private SalesService salesService;
	
	@RequestMapping(value="/per-day", method=RequestMethod.GET)
	public List<SalesForDay> getSales(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		return salesService.getSales(settings.getFrom(), settings.getTo(), settings.getProduct());
	}


}
