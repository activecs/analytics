package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.SegmentRestriction;

public interface SegmentRestrictionDao {

	List<SegmentRestriction> getAll();

	SegmentRestriction geById(Integer id);

	void delete(SegmentRestriction segmentRestriction);

	int delete(Integer id);

	SegmentRestriction add(SegmentRestriction segmentRestriction);

	SegmentRestriction update(SegmentRestriction segmentRestriction);
}
