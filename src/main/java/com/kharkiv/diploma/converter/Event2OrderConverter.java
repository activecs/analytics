package com.kharkiv.diploma.converter;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.dto.widget.Order;

@Component
public class Event2OrderConverter implements Converter<List<Event>, List<Order>> {

	@Override
	public List<Order> convert(List<Event> source) {
		List<Order> target = new ArrayList<>();
		for(Event event: source)
			target.add(convertEventToOrder(event));
		return target;
	}

	private Order convertEventToOrder(Event event) {
		Order order = new Order();
		order.setId(event.getLabel());
		order.setItems(parseProducts(event.getDescription()));
		return order;
	}

	private List<String> parseProducts(String products) {
		return newArrayList(Splitter.on(',').trimResults().omitEmptyStrings().split(products));
	}

}
