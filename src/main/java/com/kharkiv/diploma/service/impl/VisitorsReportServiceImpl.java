package com.kharkiv.diploma.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kharkiv.diploma.converter.Session2VisitorsReportConverter;
import com.kharkiv.diploma.dao.SessionDao;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.dto.widget.VisitorReport;
import com.kharkiv.diploma.service.VisitorsReportService;

@Service
@Transactional
public class VisitorsReportServiceImpl implements VisitorsReportService {

	@Inject
	private Session2VisitorsReportConverter visitorReportConverter;
	@Inject
	private SessionDao sessionDao;

	@Override
	public VisitorReport getReport() {
		List<Session> sessions = sessionDao.getAll();
		return visitorReportConverter.convert(sessions);
	}

}
