package com.kharkiv.diploma.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.VisitorReport;
import com.kharkiv.diploma.service.VisitorsReportService;

@RestController
@RequestMapping("/visitor")
public class VisitorsReportWidgetController {
	
	@Inject
	private VisitorsReportService visitorsReportService;
	
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public VisitorReport getBrowserUsage(){
		return visitorsReportService.getReport();
	}
}
