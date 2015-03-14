package com.kharkiv.diploma.dto.analytics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "segment_restriction")
public class SegmentRestriction extends BaseEntity {

	private static final long serialVersionUID = -294328104847128198L;
	
	public enum RestrictionType {
		EQUAL, START_WITH, REGEX
	};
	
	@Enumerated
	private RestrictionType type;
	
	@Column
	private String startingValue;
	
	@Column
	private String endValue;
	
	@OneToOne
	private Segment segment;

	public RestrictionType getType() {
		return type;
	}

	public void setType(RestrictionType type) {
		this.type = type;
	}

	public String getStartingValue() {
		return startingValue;
	}

	public void setStartingValue(String startingValue) {
		this.startingValue = startingValue;
	}

	public String getEndValue() {
		return endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	
}
