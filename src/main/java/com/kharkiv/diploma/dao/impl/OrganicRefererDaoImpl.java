package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.OrganicRefererDao;
import com.kharkiv.diploma.dto.analytics.OrganicReferer;
import com.kharkiv.diploma.util.QueryNamesConstants.OrganicRefererQueries;

@Repository("organicRefererDao")
public class OrganicRefererDaoImpl implements OrganicRefererDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<OrganicReferer> getAll() {
		 TypedQuery<OrganicReferer> query = em.createNamedQuery(OrganicRefererQueries.GET_ALL, OrganicReferer.class);
	     return query.getResultList();
	}

}
