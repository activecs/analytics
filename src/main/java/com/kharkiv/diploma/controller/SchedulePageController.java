package com.kharkiv.diploma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/schedule")
public class SchedulePageController {

	private static final String PAGE_SCHEDULE = "schedule";


	@RequestMapping(method = RequestMethod.GET)
	public String getPage(Model model) {
		return PAGE_SCHEDULE;
	}
}