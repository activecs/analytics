package com.kharkiv.diploma.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.dto.widget.Browser;
import com.kharkiv.diploma.dto.widget.BrowserUsage;

@Component
public class Session2BrowserUsageConverter implements Converter<List<Session>, List<BrowserUsage>> {

	@Override
	public List<BrowserUsage> convert(List<Session> sessions) {
		List<BrowserUsage> target = new ArrayList<>();
		Multiset<Browser> usagesCounter = aggregateBrowserUsage(sessions);
		populateUsage(target, usagesCounter);		
		return target;
	}

	private Multiset<Browser> aggregateBrowserUsage(List<Session> sessions) {
		Multiset<Browser> usagesCounter = HashMultiset.create();
		for(Session session : sessions)
			registerBrowser(usagesCounter, session);
		return usagesCounter;
	}

	private void registerBrowser(Multiset<Browser> usagesCounter, Session session) {
		String browserName = session.getNavigation().getBrowser();
		usagesCounter.add(new Browser(browserName));
	}
	
	private void populateUsage(List<BrowserUsage> target, Multiset<Browser> usagesCounter) {
		for(Browser brow: usagesCounter.elementSet())
			target.add(new BrowserUsage(brow, usagesCounter.count(brow)));
	}

}
