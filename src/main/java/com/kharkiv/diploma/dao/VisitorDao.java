package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Visitor;

public interface VisitorDao {

	List<Visitor> getAll();

	Visitor geById(Integer id);

	void delete(Visitor visitor);

	int delete(Integer id);

	Visitor add(Visitor visitor);

	Visitor update(Visitor visitor);
}
