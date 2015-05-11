package com.kharkiv.diploma.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kharkiv.diploma.dto.widget.SalesDistribution;
import com.kharkiv.diploma.dto.widget.SalesForDay;

@Component
public class SalesForDay2SalesDistributionConverter implements Converter<List<SalesForDay>, List<SalesDistribution>> {

	@Override
	public List<SalesDistribution> convert(List<SalesForDay> source) {
		List<SalesDistribution> target = new ArrayList<>();
		Multimap<Integer, SalesForDay> salesGroupedByAmount = groupSalesWithEqualSoldAmount(source);
		for(Integer soldAmount: salesGroupedByAmount.keySet()){
			Collection<SalesForDay> salesWithEqualSoldAmount = salesGroupedByAmount.get(soldAmount);
			target.add(new SalesDistribution(soldAmount, salesWithEqualSoldAmount.size()));
		}
		return target;
	}

	private HashMultimap<Integer, SalesForDay> groupSalesWithEqualSoldAmount(List<SalesForDay> source) {
		HashMultimap<Integer, SalesForDay> multimap = HashMultimap.create();
		for(SalesForDay sales : source)
			multimap.put(sales.getAmount(), sales);
		return multimap;
	}

}
