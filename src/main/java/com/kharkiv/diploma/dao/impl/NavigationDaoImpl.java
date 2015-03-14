package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.NavigationDao;
import com.kharkiv.diploma.dto.analytics.Navigation;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("navigationDao")
public class NavigationDaoImpl implements NavigationDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Navigation> getAll() {
		 TypedQuery<Navigation> query = em.createNamedQuery(UserQueries.GET_ALL, Navigation.class);
	     return query.getResultList();
	}

	@Override
	public Navigation geById(Integer id) {
		 TypedQuery<Navigation> query = em.createNamedQuery(UserQueries.GET_BY_ID, Navigation.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Navigation navigation) {
		em.remove(navigation);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Navigation> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Navigation.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Navigation add(Navigation navigation) {
		em.persist(navigation);
        em.flush();
        return navigation;
	}

	@Override
	public Navigation update(Navigation navigation) {
		 return em.merge(navigation);
	}

}
