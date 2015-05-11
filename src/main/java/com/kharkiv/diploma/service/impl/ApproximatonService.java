package com.kharkiv.diploma.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.kharkiv.diploma.util.ApproximationPolinom;
import com.kharkiv.diploma.util.SystemEquationsBuilder;
import com.kharkiv.diploma.util.SystemEquationsSolver;

@Component
public class ApproximatonService {

	private static final int POLINOMIAL_DEGREE = 2;
	@Inject
	private SystemEquationsBuilder systemEquationsBuilder;
	@Inject
	private SystemEquationsSolver systemEquationsSolver;

	public ApproximatonService() {
		systemEquationsBuilder = new SystemEquationsBuilder();
		systemEquationsSolver = new SystemEquationsSolver();
	}

	public double[][] approximate(double[][] values) {
		ApproximationPolinom polinom = buildPolinom(values);
		double[][] approximatedValues = new double[values.length][values[0].length];
		copyXvaluesToResult(values, approximatedValues);
		fullfillApproximatedYValues(polinom, approximatedValues);
		return approximatedValues;
	}

	private void fullfillApproximatedYValues(ApproximationPolinom polinom, double[][] approximatedValues) {
		for (int i = 0; i < approximatedValues[0].length; i++)
			approximatedValues[1][i] = polinom.getValue(approximatedValues[0][i]);
	}

	private void copyXvaluesToResult(double[][] values, double[][] approximatedValues) {
		System.arraycopy(values[0], 0, approximatedValues[0], 0, values[0].length);
	}

	private ApproximationPolinom buildPolinom(double[][] values) {
		double[][] systemIndexes = systemEquationsBuilder.makeSystem(values, POLINOMIAL_DEGREE);
		double[] polinomIndexes = systemEquationsSolver.solve(systemIndexes);
		return new ApproximationPolinom(polinomIndexes);
	}

}
