package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.util.PaginationData;

public interface EventDao {

	List<Event> getAll();
	
	List<Event> getAll(PaginationData pagination);
	
	List<Event> get(String category, String action, PaginationData pagination);

	Event geById(Integer id);

	void delete(Event page);

	int delete(Integer id);

	Event add(Event page);

	Event update(Event page);
}
