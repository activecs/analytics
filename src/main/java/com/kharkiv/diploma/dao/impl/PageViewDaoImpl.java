package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.PageViewDao;
import com.kharkiv.diploma.dto.analytics.PageView;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("pageViewDao")
public class PageViewDaoImpl implements PageViewDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<PageView> getAll() {
		 TypedQuery<PageView> query = em.createNamedQuery(UserQueries.GET_ALL, PageView.class);
	     return query.getResultList();
	}

	@Override
	public PageView geById(Integer id) {
		 TypedQuery<PageView> query = em.createNamedQuery(UserQueries.GET_BY_ID, PageView.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(PageView pageView) {
		em.remove(pageView);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<PageView> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, PageView.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public PageView add(PageView pageView) {
		em.persist(pageView);
        em.flush();
        return pageView;
	}

	@Override
	public PageView update(PageView pageView) {
		 return em.merge(pageView);
	}

}
