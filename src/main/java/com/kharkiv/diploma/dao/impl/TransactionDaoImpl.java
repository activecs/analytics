package com.kharkiv.diploma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kharkiv.diploma.dao.TransactionDao;
import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.util.QueryNamesConstants.UserQueries;

@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Transaction> getAll() {
		 TypedQuery<Transaction> query = em.createNamedQuery(UserQueries.GET_ALL, Transaction.class);
	     return query.getResultList();
	}

	@Override
	public Transaction geById(Integer id) {
		 TypedQuery<Transaction> query = em.createNamedQuery(UserQueries.GET_BY_ID, Transaction.class);
	     return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Transaction transaction) {
		em.remove(transaction);
	}

	@Override
	public int delete(Integer id) {
		TypedQuery<Transaction> query = em.createNamedQuery(UserQueries.DELETE_BY_ID, Transaction.class);
        return query.setParameter("id", id).executeUpdate();
	}

	@Override
	public Transaction add(Transaction transaction) {
		em.persist(transaction);
        em.flush();
        return transaction;
	}

	@Override
	public Transaction update(Transaction transaction) {
		 return em.merge(transaction);
	}

}
