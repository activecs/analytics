package com.kharkiv.diploma.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.dao.SessionDao;
import com.kharkiv.diploma.dto.analytics.PageView;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.service.OrganicRefererService;
import com.kharkiv.diploma.service.SessionService;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
	
	private static final int FIRST = 0;
	
	@Inject
	private SessionDao sessionDao;
	@Inject
	private OrganicRefererService organicRefererService; 

	@Override
	public List<Session> getAll() {
		return sessionDao.getAll();
	}

	@Override
	public Session geById(Integer id) {
		return sessionDao.getById(id);
	}

	@Override
	public void delete(Session session) {
		sessionDao.delete(session);
	}

	@Override
	public int delete(Integer id) {
		return sessionDao.delete(id);
	}

	@Override
	public Session add(Session session) {
		return sessionDao.add(session);
	}

	@Override
	public Session update(Session session) {
		return sessionDao.update(session);
	}
	
	@Override
	public PageView getFirstViewFromSession(Session session) {
		List<PageView> views = session.getPageViews();
		PageView firstView = views.get(FIRST);
		for(PageView view : views)
			if(view.getDate().before(firstView))
				firstView = view;
		return firstView;
	}
	
	@Override
	public boolean isOrganic(Session session) {
		PageView firstView = getFirstViewFromSession(session);
		String referer = firstView.getReferer();
		return organicRefererService.isOrganic(referer);
	}
}
