package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Segment;

public interface SegmentDao {

	List<Segment> getAll();

	Segment geById(Integer id);

	void delete(Segment segment);

	int delete(Integer id);

	Segment add(Segment segment);

	Segment update(Segment segment);
}
