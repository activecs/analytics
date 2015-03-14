package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.PageDao;
import com.kharkiv.diploma.dto.analytics.Page;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("pageDao")
public class PageDaoImpl implements PageDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Page> getAll() {
		 TypedQuery<Page> query = em.createNamedQuery(UserQueries.GET_ALL, Page.class);
	     return query.getResultList();
	}

	@Override
	public Page geById(Integer id) {
		 TypedQuery<Page> query = em.createNamedQuery(UserQueries.GET_BY_ID, Page.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Page page) {
		em.remove(page);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Page> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Page.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Page add(Page page) {
		em.persist(page);
        em.flush();
        return page;
	}

	@Override
	public Page update(Page page) {
		 return em.merge(page);
	}

}
