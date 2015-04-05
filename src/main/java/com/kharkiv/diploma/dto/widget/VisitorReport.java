package com.kharkiv.diploma.dto.widget;

import java.util.Set;

public class VisitorReport {
	
	private Set<VisitorRevision> revisions;
	private int visits;
	private double organic;
	private double referral;

	public Set<VisitorRevision> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<VisitorRevision> revisions) {
		this.revisions = revisions;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public void setOrganic(double organic) {
		this.organic = organic;
	}

	public double getReferral() {
		return referral;
	}

	public void setReferral(double referral) {
		this.referral = referral;
	}

	public double getOrganic() {
		return organic;
	}
	
}
