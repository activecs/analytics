package com.kharkiv.diploma.service;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.PageView;
import com.kharkiv.diploma.dto.analytics.Session;

public interface SessionService {
	
	List<Session> getAll();

	Session geById(Integer id);

	void delete(Session session);

	int delete(Integer id);

	Session add(Session session);

	Session update(Session session);
	
	PageView getFirstViewFromSession(Session session);
	
	boolean isOrganic(Session session);
	
}
