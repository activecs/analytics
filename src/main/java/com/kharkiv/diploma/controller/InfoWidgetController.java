package com.kharkiv.diploma.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.Info;
import com.kharkiv.diploma.service.InfoService;

@RestController
public class InfoWidgetController extends EventAbstractController {
		
	@Inject
	private InfoService infoService;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public Info getInfo() {
		return infoService.getInfo();
	}
		
}
