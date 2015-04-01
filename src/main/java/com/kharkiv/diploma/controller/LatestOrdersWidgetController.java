package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharkiv.diploma.dto.widget.Order;
import com.kharkiv.diploma.service.EventService;

@RestController
public class LatestOrdersWidgetController extends EventAbstractController {
		
	@Inject
	private EventService eventService;

	@RequestMapping(value = "/order/latest", method = RequestMethod.GET)
	public List<Order> getBrowserUsage() {
		return eventService.getAllLatestOrders();
	}
		
}
