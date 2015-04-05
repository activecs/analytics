package com.kharkiv.diploma.service;

import java.io.IOException;

import com.kharkiv.diploma.dto.analytics.Location;

public interface GeocodeService {
	
	void fillUpGeometryInformation(Location location) throws IOException;

}
