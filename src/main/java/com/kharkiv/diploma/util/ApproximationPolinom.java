package com.kharkiv.diploma.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ApproximationPolinom {
	
	private double a;
	private double b;
	private double c;
	private double d;
	private double e;
	
	public ApproximationPolinom(double[] indexes) {
		super();
		if(indexes.length>=1)
			this.a = indexes[0];
		if(indexes.length>=2)
			this.b = indexes[1];
		if(indexes.length>=3)
			this.c = indexes[2];
		if(indexes.length>=4)
			this.d = indexes[3];
		if(indexes.length>=5)
			this.e = indexes[4];
		if(indexes.length>=6)
			throw new IllegalArgumentException();
	}
	
	public double getValue(double x){
		double result = 0.;
		result += a*Math.pow(x, 0);;
		result += b*Math.pow(x, 1);
		result += c*Math.pow(x, 2);
		result += d*Math.pow(x, 3);
		result += e*Math.pow(x, 4);
		BigDecimal roundedResult = new BigDecimal(result);
		return roundedResult.setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	
}
