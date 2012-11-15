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
	
	public static boolean isValid(int a, int b, int c)
	{
		int x = a*c; int y = (int) Math.sqrt(x); int z = 1;
		if(c == 0)
			return true;
		else if(c < 0)
			z = -1;
		for(int i = 1; i <= y; i++)
		{
			int j = x/i*z;
			if(i + j == b || (0-j) + (0-i) == b)
				return true;
		}
		return false;
	}

	public void generateStandardForm(){
		a = leftCo * rightCo;
		b = leftCo * r + rightCo * s;
		c = r * s;
	}
	
	public String toString(){
		return a + "x^2 + "+ b +"x + "+c;
	}
	
	public boolean equals(QEquation q)
	{
		return q.a == a && q.b == b && q.c == c;
	}
	
	public static QEquation factor(int a, int b, int c)
	{
		if(b == 0 && (a > 0)^(c > 0) && Math.sqrt(c/a) % 1 == 0)
		{
			return new QEquation(a, (int)(a*Math.sqrt(c/a)), 1, (int)(0-Math.sqrt(c/a)));
		}
		if(c == 0)
		{
			return new QEquation(a, b, 1, 0);
		}
		
		int n = a*c; int m = (int) Math.sqrt(n);
		int j = 0;
		for(int i = 0; i <= m; i++)
		{
			j = n/i;
			if(i + j == b)
			{
				break;
			}
			if((0-j) + (0-i) == b)
			{
				j = -j;
				break;
			}
		}
		if(j != 0)
		{
			int i = n/j;
			int x = gcd(a, i);
			int y = gcd(j, c);
			return new QEquation(x, y, j/y, c/y);
		}
		return null;
	}
	
	public static int gcd(int a, int b)
	{
		if(a == 0)
			return b;
		return gcd(b%a, a);
	}
}