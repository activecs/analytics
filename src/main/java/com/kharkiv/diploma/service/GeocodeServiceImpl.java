package com.kharkiv.diploma.service;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.LatLng;
import com.kharkiv.diploma.dto.analytics.Location;

@Service
public class GeocodeServiceImpl implements GeocodeService {
	
	@Inject
	private Geocoder geocoder;
	@Value("${geocode.query.language}")
	private String geocodeRequestLanguage;
	
	@Override
	public void fillUpGeometryInformation(Location location) throws IOException {
		notNull(location.getCity());
		notNull(location.getCountry());
		String queryValue = buildGeometryQuery(location);
		GeocodeResponse geocoderResponse = sendRequest(queryValue);
		populateGeometry(location, geocoderResponse);
	}

	private String buildGeometryQuery(Location location) {
		return format("%s, %s", location.getCity(), location.getCountry());
	}

	private GeocodeResponse sendRequest(String queryValue) throws IOException {
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(queryValue).setLanguage(geocodeRequestLanguage).getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		return geocoderResponse;
	}
	
	private void populateGeometry(Location location, GeocodeResponse geocoderResponse) {
		LatLng locationGeometry = geocoderResponse.getResults().get(0).getGeometry().getLocation();
		BigDecimal latitude = locationGeometry.getLat();
		BigDecimal longitude = locationGeometry.getLng();
		location.setLongitude(longitude.doubleValue());
		location.setLatitude(latitude.doubleValue());
	}

}
