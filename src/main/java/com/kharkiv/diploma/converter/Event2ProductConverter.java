package com.kharkiv.diploma.converter;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.dto.widget.Product;

@Component
public class Event2ProductConverter implements Converter<List<Event>, List<Product>> {

	private Pattern productNameSkuPricePattern = Pattern.compile("(.*)/(.*)/(\\d+(.\\d*)?)");

	@Override
	public List<Product> convert(List<Event> source) {
		List<Product> target = new ArrayList<>();
		for(Event event: source)
			target.add(convertEventToProduct(event));
		return target;
	}

	private Product convertEventToProduct(Event event) {
		Product product = new Product();
		product.setName(parseProductName(event.getLabel()));
		product.setSKU(parseProductSKU(event.getLabel()));
		product.setPrice(new BigDecimal(parseProductBasePrice(event.getLabel())));
		return product;
	}

	private String parseProductName(String productNameAndSKUAndPrice) {
		Matcher matcher = productNameSkuPricePattern.matcher(productNameAndSKUAndPrice);
		if(matcher.find())
			return matcher.group(1);
		return EMPTY;
	}
	
	private String parseProductSKU(String productNameAndSKUAndPrice) {
		Matcher matcher = productNameSkuPricePattern.matcher(productNameAndSKUAndPrice);
		if(matcher.find())
			return matcher.group(2);
		return EMPTY;
	}
	
	private String parseProductBasePrice(String productNameAndSKUAndPrice) {
		Matcher matcher = productNameSkuPricePattern.matcher(productNameAndSKUAndPrice);
		if(matcher.find())
			return matcher.group(3);
		return "0";
	}

}
