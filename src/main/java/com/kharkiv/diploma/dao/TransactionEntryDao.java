package com.kharkiv.diploma.dao;

import java.util.List;

import com.kharkiv.diploma.dto.analytics.TransactionEntry;

public interface TransactionEntryDao {

	List<TransactionEntry> getAll();

	TransactionEntry geById(Integer id);

	void delete(TransactionEntry transactionEntry);

	int delete(Integer id);

	TransactionEntry add(TransactionEntry transactionEntry);

	TransactionEntry update(TransactionEntry transactionEntry);
}
