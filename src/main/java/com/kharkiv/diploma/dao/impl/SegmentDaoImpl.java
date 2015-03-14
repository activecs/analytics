package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.SegmentDao;
import com.kharkiv.diploma.dto.analytics.Segment;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("segmentDao")
public class SegmentDaoImpl implements SegmentDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Segment> getAll() {
		 TypedQuery<Segment> query = em.createNamedQuery(UserQueries.GET_ALL, Segment.class);
	     return query.getResultList();
	}

	@Override
	public Segment geById(Integer id) {
		 TypedQuery<Segment> query = em.createNamedQuery(UserQueries.GET_BY_ID, Segment.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Segment segment) {
		em.remove(segment);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Segment> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Segment.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Segment add(Segment segment) {
		em.persist(segment);
        em.flush();
        return segment;
	}

	@Override
	public Segment update(Segment segment) {
		 return em.merge(segment);
	}

}
