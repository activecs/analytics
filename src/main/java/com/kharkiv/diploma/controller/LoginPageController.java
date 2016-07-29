package com.kharkiv.diploma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginPageController {

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(Model model) {
		return "login";
	}
}