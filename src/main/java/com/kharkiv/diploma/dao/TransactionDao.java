package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.Transaction;

public interface TransactionDao {

	List<Transaction> getAll();

	Transaction geById(Integer id);

	void delete(Transaction transaction);

	int delete(Integer id);

	Transaction add(Transaction transaction);

	Transaction update(Transaction transaction);
}
