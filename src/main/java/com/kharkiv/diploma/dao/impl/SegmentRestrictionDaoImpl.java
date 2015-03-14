package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.SegmentRestrictionDao;
import com.kharkiv.diploma.dto.analytics.SegmentRestriction;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("segmentRestrictionDao")
public class SegmentRestrictionDaoImpl implements SegmentRestrictionDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<SegmentRestriction> getAll() {
		 TypedQuery<SegmentRestriction> query = em.createNamedQuery(UserQueries.GET_ALL, SegmentRestriction.class);
	     return query.getResultList();
	}

	@Override
	public SegmentRestriction geById(Integer id) {
		 TypedQuery<SegmentRestriction> query = em.createNamedQuery(UserQueries.GET_BY_ID, SegmentRestriction.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(SegmentRestriction segmentRestriction) {
		em.remove(segmentRestriction);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<SegmentRestriction> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, SegmentRestriction.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public SegmentRestriction add(SegmentRestriction segmentRestriction) {
		em.persist(segmentRestriction);
        em.flush();
        return segmentRestriction;
	}

	@Override
	public SegmentRestriction update(SegmentRestriction segmentRestriction) {
		 return em.merge(segmentRestriction);
	}

}
