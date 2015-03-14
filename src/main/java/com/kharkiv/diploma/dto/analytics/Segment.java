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
@Table(name = "segment")
public class Segment extends BaseEntity {

	private static final long serialVersionUID = 2060593943057113252L;
	
	public enum SegmentType {}
	
	@Column
	private String name;
	
	@Enumerated
	private SegmentType type;
	
	@OneToOne
	private SegmentRestriction restriction;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}

	public SegmentRestriction getRestriction() {
		return restriction;
	}

	public void setRestriction(SegmentRestriction restriction) {
		this.restriction = restriction;
	}
	
}
