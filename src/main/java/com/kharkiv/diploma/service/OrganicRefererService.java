package com.kharkiv.diploma.service;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.OrganicReferer;

public interface OrganicRefererService {

	List<OrganicReferer> getAll();
	
	boolean isOrganic(String referer);

}
