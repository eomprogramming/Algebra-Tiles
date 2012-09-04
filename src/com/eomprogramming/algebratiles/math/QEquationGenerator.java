package com.eomprogramming.algebratiles.math;

public class QEquationGenerator {
	
	public static final int MIN = -3;
	public static final int MAX = 3;
	
	public static QEquation generateRandom(){
		QEquation qe = new QEquation();
		qe.setLeftCoefficient(random(MIN,MAX,true));
		qe.setRightCoefficient(random(MIN,MAX,true));
		
		qe.setS(random(MIN,MAX, true));
		qe.setR(random(MIN,MAX, false));
		
		qe.generateStandardForm();
		
		return qe;		
	}
	
	private static int random(int low, int high, boolean zero){
		
		if(high < low){
			int temp = low;
			low = high;
			high = temp;
		}
		int num = (int)((Math.random()-0.5)*(high-low + 1));
		while(zero)
		{
			num = (int)((Math.random()-0.5)*(high-low + 1));
			if(num != 0)
				break;
		}
		return num;   
	}	
}
