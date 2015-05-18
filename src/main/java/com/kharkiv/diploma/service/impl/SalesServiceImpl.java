package com.kharkiv.diploma.service.impl;

import static com.google.common.math.DoubleMath.mean;
import static java.util.Collections.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.kharkiv.diploma.converter.Array2SalesApproximationConverter;
import com.kharkiv.diploma.converter.SalesDistribution2ArrayConverter;
import com.kharkiv.diploma.converter.SalesForDay2SalesDistributionConverter;
import com.kharkiv.diploma.dto.analytics.Transaction;
import com.kharkiv.diploma.dto.analytics.TransactionEntry;
import com.kharkiv.diploma.dto.widget.DistributionParameters;
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
		distribution = standardizeDistribution(distribution);
		double[][] approximatedResult = approximatonService.approximate(distribution2ArrayConverter.convert(distribution));
		return array2SalesApproximationConverter.convert(approximatedResult);
	}

	private List<SalesDistribution> standardizeDistribution(final List<SalesDistribution> distribution) {
		final double totalDayAmount = getDayNumber(distribution);
		Collection<SalesDistribution> standardizedDistribution = Collections2.transform(distribution, new Function<SalesDistribution, SalesDistribution>() {
			@Override
			public SalesDistribution apply(SalesDistribution input) {
				BigDecimal dayNumber = new BigDecimal(input.getDayNumber()/totalDayAmount);
				dayNumber = dayNumber.setScale(3, RoundingMode.HALF_UP);
				input.setDayNumber(dayNumber.doubleValue());
				return input;
			}
		});
		return Lists.newArrayList(standardizedDistribution);
	}

	private double getDayNumber(List<SalesDistribution> distributions) {
		double number = 0;
		for(SalesDistribution distaribution : distributions)
			number += distaribution.getDayNumber();
		return number;
	}

	@Override
	public DistributionParameters getDistributionParameters(Date startDate,	Date endDate, Product product) {
		DistributionParameters parameters = new DistributionParameters();
		List<SalesForDay> salesDistribution = getSales(startDate, endDate, product);
		double[] distributionValues = getSalesAmount(salesDistribution);
		parameters.setSigma(mean(distributionValues));
		parameters.setMin(Doubles.min(distributionValues));
		parameters.setMax(Doubles.max(distributionValues));
		parameters.setDispersion(standardDeviation(distributionValues));
		return parameters;
	}

	private double[] getSalesAmount(List<SalesForDay> salesDistribution) {
		double[] distributionValues = new double[salesDistribution.size()];
		for(int i = 0; i<salesDistribution.size(); i++){
			SalesForDay sale = salesDistribution.get(i);
			distributionValues[i] = sale.getAmount();
		}
		return distributionValues;
	}

	private Double standardDeviation(double[] values) {
		double arithmeticMean = mean(values);
		double deviation = 0d;
		for(int i=0; i<values.length; i++)
			deviation += Math.pow((values[i] - arithmeticMean), 2);
		deviation = deviation/(values.length - 1);
		deviation = Math.sqrt(deviation);
		BigDecimal standardDeviation = new BigDecimal(deviation);
		return standardDeviation.setScale(3,RoundingMode.HALF_UP).doubleValue();
	}
	
}
