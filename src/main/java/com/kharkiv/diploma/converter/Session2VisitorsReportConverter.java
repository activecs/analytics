package com.kharkiv.diploma.converter;

import static java.math.BigDecimal.ONE;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kharkiv.diploma.dto.analytics.Location;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.dto.widget.VisitorReport;
import com.kharkiv.diploma.dto.widget.VisitorRevision;
import com.kharkiv.diploma.service.GeocodeService;
import com.kharkiv.diploma.service.SessionService;

@Component
public class Session2VisitorsReportConverter implements Converter<List<Session>, VisitorReport> {
	
	private static final Logger LOG = LoggerFactory.getLogger(Session2VisitorsReportConverter.class);
	
	@Inject
	private GeocodeService geocodeService;
	@Inject
	private SessionService sessionService;
	
	@Override
	public VisitorReport convert(List<Session> source) {
		VisitorReport report = new VisitorReport();
		populateVisitsAmount(source, report);
		populateOrganicPart(source, report);
		populateRevisions(source, report);
		return report;
	}

	private void populateVisitsAmount(List<Session> source, VisitorReport report) {
		report.setVisits(source.size());
	}

	private void populateOrganicPart(List<Session> source, VisitorReport report) {
		BigDecimal organicSessionAmount = countOrganicViews(source);
		BigDecimal totalSessionAmount = BigDecimal.valueOf(source.size());
		BigDecimal organic = organicSessionAmount.divide(totalSessionAmount, 2, RoundingMode.HALF_UP);
		report.setOrganic(organic.movePointRight(2).doubleValue());
		report.setReferral(ONE.subtract(organic).movePointRight(2).doubleValue());
	}

	private BigDecimal countOrganicViews(List<Session> source) {
		int organicCounter = 0;
		for(Session session : source)
			if(hasPageViews(session) && sessionService.isOrganic(session))
				organicCounter++;
		return BigDecimal.valueOf(organicCounter);
	}

	private boolean hasPageViews(Session session) {
		return !session.getPageViews().isEmpty();
	}

	private void populateRevisions(List<Session> source, VisitorReport report) {
		Set<VisitorRevision> revisions = new HashSet<>();
		Multimap<Location, Session> sessions = groupSessionsWithEqualLocations(source);
		for(Location location: sessions.keySet()){
			VisitorRevision visitorRevision = new VisitorRevision();
			populateVisitorRevision(sessions, location, visitorRevision);
			revisions.add(visitorRevision);
		}
		report.setRevisions(revisions);
	}

	private Multimap<Location, Session> groupSessionsWithEqualLocations(List<Session> source) {
		HashMultimap<Location, Session> multimap = HashMultimap.create();
		for(Session session : source)
			multimap.put(session.getLocation(), session);
		return multimap;
	}

	private void populateVisitorRevision(Multimap<Location, Session> sessions,	Location location, VisitorRevision visitorRevision) {
		clarifyGeolocation(location);
		visitorRevision.setLatitude(location.getLatitude());
		visitorRevision.setLongitude(location.getLongitude());
		visitorRevision.setDescription(location.getCity());
		visitorRevision.setAmount(sessions.get(location).size());
	}

	private void clarifyGeolocation(Location location) {
		try {
			if(location.getLatitude() == null || location.getLongitude() == null)
				geocodeService.fillUpGeometryInformation(location);
		} catch (IOException e) {
			LOG.error("Cannot clarify location data", e);
		}
	}

}
