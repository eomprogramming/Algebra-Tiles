package com.eomprogramming.algebratiles.math;

/**
 * 
 * A factorable quadratic equation:
 * 
 *  (<i>leftCo</i><b> x</b> + <i>s</i>)(<i>rightCo</i><b> x</b> + <i>r</i>)
 *  
 * @author Aly

 */
public class QEquation {
	
	//For standard form
	private int a, b, c;
	//For factored form
	private int leftCo, rightCo, s, r;
	
	public QEquation(){
		
	}

	public void setLeftCoefficient(int l){
		if(l > 0)
			leftCo = l;
	}
	
	public void setRightCoefficient(int r){
		if(r > 0)
			rightCo = r;
	}
	
	public void setS(int in_s){
		s = in_s;
	}
	
	public void setR(int in_r){
		r = in_r;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public int getLeftCo() {
		return leftCo;
	}

	public int getRightCo() {
		return rightCo;
	}

	public int getS() {
		return s;
	}

	public int getR() {
		return r;
	}

	public void generateStandardForm(){
		if(leftCo < 0 || rightCo < 0)
			return;
		
		a = leftCo * rightCo;
		b = leftCo * r + rightCo * s;
		c = r * s;
	}
	
	public String toString(){
		return a + "x^2 + "+ b +"x + "+c;
	}
}