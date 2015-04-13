package com.kharkiv.diploma.service;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.PageView;

public interface PageViewService {
	
	List<PageView> getAll();

	PageView geById(Integer id);

	void delete(PageView pageView);

	int delete(Integer id);

	PageView add(PageView pageView);

	PageView update(PageView pageView);
	
}
