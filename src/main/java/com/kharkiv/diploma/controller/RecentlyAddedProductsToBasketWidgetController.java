package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.service.EventService;

@RestController
public class RecentlyAddedProductsToBasketWidgetController extends EventAbstractController {
	
	@Inject
	private EventService eventService;
	
	@RequestMapping(value="/basket/recently-added", method=RequestMethod.GET)
	public List<Product> getRecentlyAddedProducts(){
		return eventService.getAllRecentlyAddedToBasketProducts();
	}
	
}
