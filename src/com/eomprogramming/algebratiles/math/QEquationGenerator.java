package com.eomprogramming.algebratiles.math;

public class QEquationGenerator {
	
	public static final int MIN = 0;
	public static final int MAX = 3;
	
	public static QEquation generateRandom(){
		QEquation qe = new QEquation();
		qe.setLeftCoefficient(random(1,MAX));
		qe.setRightCoefficient(random(1,MAX-qe.getLeftCo()+1));
		
		qe.setS(random(MIN+1,MAX));
		qe.setR(random(MIN,MAX-qe.getLeftCo()+1));
		
		qe.generateStandardForm();
		
		return qe;		
	}
	
	private static int random(int low, int high){
		
		if(high < low){
			int temp = low;
			low = high;
			high = temp;
		}
		
		return (int)(Math.random()*(high-low + 1)) + low;   
	}	
}
