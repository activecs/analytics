package com.kharkiv.diploma.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.dto.widget.Product;

public interface TransactionService {
	
	List<Transaction> getAll();
	
	List<Transaction> getAllForProduct(Product product);
	
	List<TransactionEntry> filterEntriesWithProduct(Transaction transaction, Product product);
	
	List<Transaction> getAllForProduct(Product product, Date date);

	Transaction geById(Integer id);

	void delete(Transaction transaction);

	int delete(Integer id);

	Transaction add(Transaction transaction);

	Transaction update(Transaction transaction);
	
	BigDecimal getTotalRevenue();
	
}
