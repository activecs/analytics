package com.kharkiv.diploma.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.dao.PageViewDao;
import com.kharkiv.diploma.dto.analytics.PageView;
import com.kharkiv.diploma.service.PageViewService;

@Service
@Transactional
public class PageViewServiceImpl implements PageViewService {
	
	@Inject
	private PageViewDao pageViewDao;
	
	@Override
	public List<PageView> getAll() {
		return pageViewDao.getAll();
	}

	@Override
	public PageView geById(Integer id) {
		return pageViewDao.getById(id);
	}

	@Override
	public void delete(PageView pageView) {
		pageViewDao.delete(pageView);
	}

	@Override
	public int delete(Integer id) {
		return pageViewDao.delete(id);
	}

	@Override
	public PageView add(PageView pageView) {
		return pageViewDao.add(pageView);
	}

	@Override
	public PageView update(PageView pageView) {
		return pageViewDao.update(pageView);
	}
	
}
