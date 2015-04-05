package com.kharkiv.diploma.service;

import static com.google.common.base.Strings.nullToEmpty;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kharkiv.diploma.dao.OrganicRefererDao;
import com.kharkiv.diploma.dto.analytics.OrganicReferer;

@Service
public class OrganicRefererServiceImpl implements OrganicRefererService {

	@Inject
	private OrganicRefererDao organicRefererDao;
	
	@Override
	public List<OrganicReferer> getAll() {
		return organicRefererDao.getAll();
	}

	@Override
	public boolean isOrganic(String referer) {
		referer = nullToEmpty(referer);
		for(OrganicReferer organicReferer : getAll())
			if(referer.contains(organicReferer.getValue()))
				return true;
		return false;
	}
	
}
