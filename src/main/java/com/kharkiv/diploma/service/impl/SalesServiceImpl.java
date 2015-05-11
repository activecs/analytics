package com.kharkiv.diploma.service.impl;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kharkiv.diploma.converter.Array2SalesApproximationConverter;
import com.kharkiv.diploma.converter.SalesDistribution2ArrayConverter;
import com.kharkiv.diploma.converter.SalesForDay2SalesDistributionConverter;
import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.dto.widget.SalesApproximation;
import com.kharkiv.diploma.dto.widget.SalesDistribution;
import com.kharkiv.diploma.dto.widget.SalesForDay;
import com.kharkiv.diploma.service.SalesService;
import com.kharkiv.diploma.service.TransactionService;

@Service("salesService")
@Transactional
public class SalesServiceImpl implements SalesService {
	
	private static final int ONE_DAY = 1;
	
	@Inject
	private TransactionService transactionService;
	@Inject
	private SalesForDay2SalesDistributionConverter salesDistributionConverter;
	@Inject
	private ApproximatonService approximatonService;
	@Inject
	private SalesDistribution2ArrayConverter distribution2ArrayConverter;
	@Inject
	private Array2SalesApproximationConverter array2SalesApproximationConverter;
	
	@Override
	public List<SalesForDay> getSales(Date startDate, Date endDate, Product product){
		Long interval = getNumberOfDays(startDate, endDate);
		List<SalesForDay> sales = new ArrayList<>();
		DateTime saleDate = new DateTime(startDate);
		for(int i=0; i<interval; i++, saleDate = saleDate.plusDays(ONE_DAY))
			sales.add(populateSaleInformation(product, saleDate));
		return sales;
	}

	private SalesForDay populateSaleInformation(Product product, DateTime saleDate) {
		SalesForDay sale = new SalesForDay();
		sale.setProduct(product);
		sale.setDate(saleDate.toDate());
		sale.setAmount(getProductQuantity(product, saleDate));
		return sale;
	}

	private int getProductQuantity(Product product, DateTime saleDate) {
		int quantity = 0;
		List<Transaction> transactions = transactionService.getAllForProduct(product, saleDate.toDate());
		for(Transaction transaction : transactions)
			quantity += getProductQuantity(product, transaction);
		return quantity;
	}

	private int getProductQuantity(Product product, Transaction transaction) {
		int quantity = 0;
		List<TransactionEntry> transactionEntries = transactionService.filterEntriesWithProduct(transaction, product);
		for(TransactionEntry entry : transactionEntries)
			quantity += entry.getQuantity();
		return quantity;
	}
	
	private long getNumberOfDays(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (24*60*60*1000) + 1;
	}

	@Override
	public List<SalesDistribution> getDistribution(Date startDate,	Date endDate, Product product) {
		List<SalesForDay> sales = getSales(startDate, endDate, product);
		sort(sales,new SalesAmountComparator());
		return salesDistributionConverter.convert(sales);
	}
	
	private class SalesAmountComparator implements Comparator<SalesForDay> {
		@Override
		public int compare(SalesForDay o1, SalesForDay o2) {
			return o1.getAmount().compareTo(o2.getAmount());
		}
	}

	@Override
	public List<SalesApproximation> getApproximatedDistribution(Date startDate,	Date endDate, Product product) {
		List<SalesDistribution> distribution = getDistribution(startDate, endDate, product);
		double[][] approximatedResult = approximatonService.approximate(distribution2ArrayConverter.convert(distribution));
		return array2SalesApproximationConverter.convert(approximatedResult);
	}
		
}