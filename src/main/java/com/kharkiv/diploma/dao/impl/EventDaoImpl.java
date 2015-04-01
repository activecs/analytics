package com.kharkiv.diploma.dao.impl;

import static java.lang.String.format;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.EventDao;
import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.util.PaginationData;
import com.kharkiv.diploma.util.QueryNamesConstants.EventQueries;

@Repository("eventDao")
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Event> getAll() {
		 TypedQuery<Event> query = em.createNamedQuery(EventQueries.GET_ALL, Event.class);
	     return query.getResultList();
	}
	
	@Override
	public List<Event> getAll(PaginationData pagination) {
		 TypedQuery<Event> query = buildPaginatedQuery(EventQueries.GET_ALL, pagination);
	     return query.getResultList();
	}

	@Override
	public Event geById(Integer id) {
		 TypedQuery<Event> query = em.createNamedQuery(EventQueries.GET_BY_ID, Event.class);
	     return query.setParameter("id", id).getSingleResult();
	}
	
	@Override
	public List<Event> get(String category, String action, PaginationData pagination) {
		 TypedQuery<Event> query = buildPaginatedQuery(EventQueries.GET_BY_CATEGORY_AND_ACTION, pagination);
		 query.setParameter("category", category);
		 query.setParameter("action", action);
	     return query.getResultList();
	}
	
	private TypedQuery<Event> buildPaginatedQuery(String queryName, PaginationData pagination) {
		String jpqlPaginatedQuery = buildQueryString(queryName, pagination);
		TypedQuery<Event> query = em.createQuery(jpqlPaginatedQuery, Event.class);
		query.setMaxResults(pagination.getPageSize());
		return query;
	}

	private String buildQueryString(String queryName, PaginationData pagination) {
		StringBuilder jpqlPaginatedQuery = new StringBuilder(getNamedQueryString(queryName));
		if(pagination.getSortBy() != null){
			jpqlPaginatedQuery.append(format(" ORDER BY e.%s", pagination.getSortBy()));
			jpqlPaginatedQuery.append(format(" %s", pagination.getSortOrder().name()));
		}
		return jpqlPaginatedQuery.toString();
	}

	private String getNamedQueryString(String queryName) {
		return em.createNamedQuery(queryName).unwrap(Query.class).getQueryString();
	}

	@Override
	public void delete(Event event) {
		em.remove(event);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Event> query = em.createNamedQuery(EventQueries.DELETE_BY_ID, Event.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Event add(Event event) {
		em.persist(event);
        em.flush();
        return event;
	}

	@Override
	public Event update(Event event) {
		 return em.merge(event);
	}

}
