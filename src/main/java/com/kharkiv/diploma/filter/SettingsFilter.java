package com.kharkiv.diploma.filter;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.dto.widget.Settings;
import com.kharkiv.diploma.service.EventService;

@Component
public class SettingsFilter extends OncePerRequestFilter {

	private static final int FIRST = 0;
	private static final int ONE = 1;
	private static final String SETTINGS_PROPERTY = "settings";
	
	@Inject
	private EventService eventService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,	HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Settings settings = (Settings)session.getAttribute(SETTINGS_PROPERTY);
		if(settings == null)
			setDefaults(session);
		filterChain.doFilter(request, response);
		request.setAttribute(SETTINGS_PROPERTY, session.getAttribute(SETTINGS_PROPERTY));
	}

	private void setDefaults(HttpSession session) {
		Settings settings = new Settings();
	    setDefaultDates(settings);
	    setDefaultProduct(settings);
	    session.setAttribute(SETTINGS_PROPERTY, settings);
	}

	private void setDefaultDates(Settings settings) {
		Calendar dateBuilder = Calendar.getInstance();
	    dateBuilder.setTime(new Date());
	    settings.setFrom(getDateFrom(dateBuilder));
	    settings.setTo(getDateTo(dateBuilder));
	}

	private Date getDateFrom(Calendar dateBuilder) {
		int i = dateBuilder.get(Calendar.DAY_OF_WEEK) - dateBuilder.getFirstDayOfWeek();
	    dateBuilder.add(Calendar.DATE, -i - 7);
	    return dateBuilder.getTime();
	}
	
	private Date getDateTo(Calendar dateBuilder) {
		dateBuilder.add(Calendar.DATE, 6);
		return dateBuilder.getTime();
	}
	
	private void setDefaultProduct(Settings settings) {
		List<Product> allProducts = eventService.getAllProducts(ONE);
		if(isNotEmpty(allProducts))
			settings.setProduct(allProducts.get(FIRST));
	}
}
