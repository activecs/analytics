package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.BrowserUsage;
import com.kharkiv.diploma.service.BrowserUsageService;

@RestController
@RequestMapping("/browser")
public class BrowserUsageWidgetController {
	
	@Inject
	private BrowserUsageService browserUsageService;
	
	@RequestMapping(value="/usage", method=RequestMethod.GET)
	public List<BrowserUsage> getBrowserUsage(){
		return browserUsageService.getUsage();
	}
}
