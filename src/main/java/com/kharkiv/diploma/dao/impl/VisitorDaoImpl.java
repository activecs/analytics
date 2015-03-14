package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.VisitorDao;
import com.kharkiv.diploma.dto.analytics.Visitor;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("visitorDao")
public class VisitorDaoImpl implements VisitorDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Visitor> getAll() {
		 TypedQuery<Visitor> query = em.createNamedQuery(UserQueries.GET_ALL, Visitor.class);
	     return query.getResultList();
	}

	@Override
	public Visitor geById(Integer id) {
		 TypedQuery<Visitor> query = em.createNamedQuery(UserQueries.GET_BY_ID, Visitor.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Visitor visitor) {
		em.remove(visitor);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Visitor> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Visitor.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Visitor add(Visitor visitor) {
		em.persist(visitor);
        em.flush();
        return visitor;
	}

	@Override
	public Visitor update(Visitor visitor) {
		 return em.merge(visitor);
	}

}
