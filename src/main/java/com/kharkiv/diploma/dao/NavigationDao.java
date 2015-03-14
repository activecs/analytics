package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Navigation;

public interface NavigationDao {

	List<Navigation> getAll();

	Navigation geById(Integer id);

	void delete(Navigation navigation);

	int delete(Integer id);

	Navigation add(Navigation navigation);

	Navigation update(Navigation navigation);
}
