package com.kharkiv.diploma.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kharkiv.diploma.converter.Session2BrowserUsageConverter;
import com.kharkiv.diploma.dao.SessionDao;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.dto.widget.BrowserUsage;
import com.kharkiv.diploma.service.BrowserUsageService;

@Service
@Transactional
public class BrowserUsageServiceImpl implements BrowserUsageService {
	
	@Inject
	private Session2BrowserUsageConverter browserUsageConverter;
	@Inject
	private SessionDao sessionDao;
	
	@Override
	public List<BrowserUsage> getUsage() {
		List<Session> sessions = sessionDao.getAll();
		return browserUsageConverter.convert(sessions);
	}

}
