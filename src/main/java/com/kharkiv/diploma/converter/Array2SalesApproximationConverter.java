package com.kharkiv.diploma.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.kharkiv.diploma.dto.widget.SalesApproximation;

@Component
public class Array2SalesApproximationConverter implements Converter<double[][], List<SalesApproximation>> {

	@Override
	public List<SalesApproximation> convert(double[][] source) {
		List<SalesApproximation> target = Lists.newArrayList();
		for (int i = 0; i < source[0].length; i++) {
			SalesApproximation approximation = new SalesApproximation();
			approximation.setX(source[0][i]);
			approximation.setY(source[1][i]);
		}
		return target;
	}

}
