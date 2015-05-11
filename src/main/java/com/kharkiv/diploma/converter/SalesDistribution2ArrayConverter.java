package com.kharkiv.diploma.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kharkiv.diploma.dto.widget.SalesDistribution;

@Component
public class SalesDistribution2ArrayConverter implements Converter<List<SalesDistribution>, double[][]> {

	@Override
	public double[][] convert(List<SalesDistribution> source) {
		double[][] target = new double[2][source.size()];
		for (int i = 0; i < source.size(); i++) {
			SalesDistribution distribution = source.get(i);
			target[0][i] = distribution.getAmount();
			target[1][i] = distribution.getDayNumber();
		}
		return target;
	}

}
