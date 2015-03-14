package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.TransactionEntryDao;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("transactionEntryDao")
public class TransactionEntryDaoImpl implements TransactionEntryDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<TransactionEntry> getAll() {
		 TypedQuery<TransactionEntry> query = em.createNamedQuery(UserQueries.GET_ALL, TransactionEntry.class);
	     return query.getResultList();
	}

	@Override
	public TransactionEntry geById(Integer id) {
		 TypedQuery<TransactionEntry> query = em.createNamedQuery(UserQueries.GET_BY_ID, TransactionEntry.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(TransactionEntry transactionEntry) {
		em.remove(transactionEntry);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<TransactionEntry> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, TransactionEntry.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public TransactionEntry add(TransactionEntry transactionEntry) {
		em.persist(transactionEntry);
        em.flush();
        return transactionEntry;
	}

	@Override
	public TransactionEntry update(TransactionEntry transactionEntry) {
		 return em.merge(transactionEntry);
	}

}
