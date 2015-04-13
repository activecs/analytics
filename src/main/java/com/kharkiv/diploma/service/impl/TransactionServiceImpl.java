package com.kharkiv.diploma.service.impl;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.dao.TransactionDao;
import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.service.CurrencyService;
import com.kharkiv.diploma.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Inject
	private TransactionDao transactionDao;
	@Inject
	private CurrencyService currencyService;
	
	@Override
	public List<Transaction> getAll() {
		List<Transaction> transactions =  transactionDao.getAll();
		for(Transaction transaction : transactions)
			updateRevenue(transaction);
		return transactions;
	}

	@Override
	public Transaction geById(Integer id) {
		Transaction transaction = transactionDao.geById(id);
		updateRevenue(transaction);
		return transactionDao.geById(id);
	}

	@Override
	public void delete(Transaction transaction) {
		transactionDao.delete(transaction);
	}

	@Override
	public int delete(Integer id) {
		return transactionDao.delete(id);
	}

	@Override
	public Transaction add(Transaction transaction) {
		updateRevenue(transaction);
		return transactionDao.add(transaction);
	}


	@Override
	public Transaction update(Transaction transaction) {
		updateRevenue(transaction);
		return transactionDao.update(transaction);
	}
	
	private void updateRevenue(Transaction transaction) {
		if(transaction == null)
			return;
		BigDecimal totalRevenue = sumTransactionRevenue(transaction);
		totalRevenue = currencyService.roundCurrency(totalRevenue);
		transaction.setRevenue(totalRevenue);
		transactionDao.update(transaction);
	}

	private BigDecimal sumTransactionRevenue(Transaction transaction) {
		BigDecimal totalRevenue = sumEntries(transaction);
		totalRevenue = totalRevenue.add(transaction.getTax());
		totalRevenue = totalRevenue.add(transaction.getShipping());
		return totalRevenue;
	}

	private BigDecimal sumEntries(Transaction transaction) {
		BigDecimal totalRevenue = ZERO;
		for(TransactionEntry entry : transaction.getEntries()){
			BigDecimal quantity = new BigDecimal(entry.getQuantity());
			BigDecimal price = entry.getPrice();
			totalRevenue = totalRevenue.add(quantity.multiply(price));
		}
		return totalRevenue;
	}

	@Override
	public BigDecimal getTotalRevenue() {
		BigDecimal totalRevenue = ZERO;
		List<Transaction> allTransactions = transactionDao.getAll();
		for(Transaction transaction : allTransactions)
			totalRevenue = totalRevenue.add(transaction.getRevenue());
		return totalRevenue;
	}
	
}
