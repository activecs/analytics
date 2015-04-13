package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.SessionDao;
import com.kharkiv.diploma.dto.analytics.Session;
import com.kharkiv.diploma.util.QueryNamesConstants.SessionsQueries;

@Repository("sessionDao")
public class SessionDaoImpl implements SessionDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Session> getAll() {
		 TypedQuery<Session> query = em.createNamedQuery(SessionsQueries.GET_ALL, Session.class);
	     return query.getResultList();
	}

	@Override
	public Session getById(Integer id) {
		 TypedQuery<Session> query = em.createNamedQuery(SessionsQueries.GET_BY_ID, Session.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Session session) {
		em.remove(session);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Session> query = em.createNamedQuery(SessionsQueries.DELETE_BY_ID, Session.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Session add(Session session) {
		em.persist(session);
        em.flush();
        return session;
	}

	@Override
	public Session update(Session session) {
		 return em.merge(session);
	}

}
