package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Page;

public interface PageDao {

	List<Page> getAll();

	Page geById(Integer id);

	void delete(Page page);

	int delete(Integer id);

	Page add(Page page);

	Page update(Page page);
}
