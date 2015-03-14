package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.LocationDao;
import com.kharkiv.diploma.dto.analytics.Location;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Location> getAll() {
		 TypedQuery<Location> query = em.createNamedQuery(UserQueries.GET_ALL, Location.class);
	     return query.getResultList();
	}

	@Override
	public Location geById(Integer id) {
		 TypedQuery<Location> query = em.createNamedQuery(UserQueries.GET_BY_ID, Location.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Location location) {
		em.remove(location);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Location> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Location.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Location add(Location location) {
		em.persist(location);
        em.flush();
        return location;
	}

	@Override
	public Location update(Location location) {
		 return em.merge(location);
	}

}
