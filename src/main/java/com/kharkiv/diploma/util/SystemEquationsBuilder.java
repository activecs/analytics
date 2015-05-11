package com.kharkiv.diploma.util;

import static java.lang.Math.pow;

import org.springframework.stereotype.Component;

@Component
public class SystemEquationsBuilder {
	
    public double[][] makeSystem(double[][] xy, int basis){
        double[][] matrix = new double[basis+1][basis+2];
        for(int i=0; i<=basis; i++){
            for(int j=0; j<=basis; j++){
                matrix[i][j] = 0;
            }
        }
        for(int i=0; i<=basis; i++){
            for(int j=0; j<=basis; j++){
                double sumA=0, sumB = 0;
                for (int k = 0; k < xy[0].length; k++){
                    sumA += pow(xy[0][k], i) * pow(xy[0][k], j);
                    sumB += xy[1][k] * pow(xy[0][k], i);
                }
                matrix[i][j] = sumA;
                matrix[i][basis+1] = sumB;
            }
        }
        return matrix;
    }

	
}
