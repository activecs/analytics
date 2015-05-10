package com.kharkiv.diploma.service.impl;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Predicate;
import com.kharkiv.diploma.dao.TransactionDao;
import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.dto.widget.Product;
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
	public List<Transaction> getAllForProduct(Product product) {
		List<Transaction> transactions = getAll();
		Collection<Transaction> transactionWithProduct = filter(transactions, new TransactionProductFilter(product.getSKU()));
		return newArrayList(transactionWithProduct);
	}
	
	
	private class TransactionProductFilter implements Predicate<Transaction> {
		private String productSKU;
		
		public TransactionProductFilter(String productSKU) {
			this.productSKU = productSKU;
		}
		
		@Override
		public boolean apply(Transaction transaction) {
			Set<TransactionEntry> transactionEntries = transaction.getEntries();
			Collection<TransactionEntry> entriesWithProduct = filter(transactionEntries, new TransactionEntryProductFilter(productSKU)); 			
			return isNotEmpty(entriesWithProduct);
		}
	}
	
	@Override
	public List<TransactionEntry> filterEntriesWithProduct(Transaction transaction, Product product) {
		Collection<TransactionEntry> entriesWithProduct = filter(transaction.getEntries(), new TransactionEntryProductFilter(product.getSKU()));
		return newArrayList(entriesWithProduct);
	}
	
	private class TransactionEntryProductFilter implements Predicate<TransactionEntry> {
		private String productSKU;
		
		public TransactionEntryProductFilter(String productSKU) {
			this.productSKU = productSKU;
		}
		
		@Override
		public boolean apply(TransactionEntry entry) {
			return productSKU.equalsIgnoreCase(entry.getSKU());
		}
	}

	@Override
	public List<Transaction> getAllForProduct(Product product, Date date) {
		List<Transaction> transactionWithProduct = getAllForProduct(product);
		return newArrayList(filter(transactionWithProduct, new TransactionDateFilter(date)));
	}
	
	private class TransactionDateFilter implements Predicate<Transaction> {
		private Date date;
		
		public TransactionDateFilter(Date date) {
			this.date = date;
		}
		
		@Override
		public boolean apply(Transaction item) {
			Calendar transactionDate = item.getSession().getOpen();
			return DateUtils.isSameDay(transactionDate.getTime(), date);
		}
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
