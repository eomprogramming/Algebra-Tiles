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
	
	public QEquation(int a, int b, int c, int d){
		leftCo = a;
		s = b;
		rightCo = c;
		r = d;
		this.generateStandardForm();
	}

	public void setLeftCoefficient(int l){
		leftCo = l;
	}
	
	public void setRightCoefficient(int r){
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
		a = leftCo * rightCo;
		b = leftCo * r + rightCo * s;
		c = r * s;
	}
	
	@Override
	public String toString(){
		return a + "x^2 + "+ b +"x + "+c;
	}
	
	public boolean equals(QEquation q)
	{
		return q.a == a && q.b == b && q.c == c;
	}
}