package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.controller.form.SettingsForm;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.service.EventService;

@RestController
@RequestMapping("/setup")
public class SetupController extends EventAbstractController {
	
	@Inject
	private EventService eventService;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public List<Product> getProducts(){
		return eventService.getAllProducts();
	}
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public void setupAdminView(@RequestBody SettingsForm settingsForm){
		System.out.println("la");
	}
	
}
