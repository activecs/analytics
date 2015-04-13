package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Session;

public interface SessionDao {

	List<Session> getAll();

	Session getById(Integer id);

	void delete(Session session);

	int delete(Integer id);

	Session add(Session session);

	Session update(Session session);
}
