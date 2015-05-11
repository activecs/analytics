package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.DistributionParameters;
import com.kharkiv.diploma.dto.widget.SalesApproximation;
import com.kharkiv.diploma.dto.widget.SalesDistribution;
import com.kharkiv.diploma.dto.widget.SalesForDay;
import com.kharkiv.diploma.dto.widget.Settings;
import com.kharkiv.diploma.service.SalesService;

@RestController
@RequestMapping("/sales")
public class DistributionChartController {
	
	private static final String SETTINGS_PROPERTY = "settings";
	@Inject
	private SalesService salesService;
	
	@RequestMapping(value="/per-day", method=RequestMethod.GET)
	public List<SalesForDay> getSales(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		return salesService.getSales(settings.getFrom(), settings.getTo(), settings.getProduct());
	}

	@RequestMapping(value="/distribution", method=RequestMethod.GET)
	public List<SalesDistribution> getDistribution(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		return salesService.getDistribution(settings.getFrom(), settings.getTo(), settings.getProduct());
	}
	
	@RequestMapping(value="/distribution/approximated", method=RequestMethod.GET)
	public List<SalesApproximation> getApproximatedDistribution(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		return salesService.getApproximatedDistribution(settings.getFrom(), settings.getTo(), settings.getProduct());
	}
	
	@RequestMapping(value="/distribution/parameters", method=RequestMethod.GET)
	public DistributionParameters getDistributionParameters(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		return salesService.getDistributionParameters(settings.getFrom(), settings.getTo(), settings.getProduct());
	}
}
