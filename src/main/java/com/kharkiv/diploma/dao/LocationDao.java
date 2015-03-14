package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Location;

public interface LocationDao {

	List<Location> getAll();

	Location geById(Integer id);

	void delete(Location location);

	int delete(Integer id);

	Location add(Location location);

	Location update(Location location);
}
