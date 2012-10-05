package com.eomprogramming.algebratiles.model;


/**
 * A tile
 * @author Aly
 *
 */
public class Tile {
	
	/**
	 * Tile type
	 */
	public static final int X_SQUARED = 0, X= 1, ONE = 2, PLUS = 3, EMPTY = 4;
	
	private int type;
	private boolean positive;
	
	public Tile()
	{
		type = -1;
		positive = true;
	}
	
	public Tile(int type, boolean isPositive){
		setPositive(isPositive);
		setType(type);
	}
	
	public void setType(int in_type){
		type = in_type;
	}

	public int getType() {
		return type;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}
	
	public String getSymbol() {
		switch(type){
			case Tile.X_SQUARED:
				return "2";
			case Tile.X:
				return "x";
			case Tile.ONE:
				return "1";
			case Tile.PLUS:
				return "+";
			case Tile.EMPTY:
				return " ";
			default:
				return " ";
		}
	}
	
	public static String getSymbol(int type) {
		switch(type){
			case Tile.X_SQUARED:
				return "X^2";
			case Tile.X:
				return "X";
			case Tile.ONE:
				return "1";
			case Tile.PLUS:
				return "+";
			case Tile.EMPTY:
				return " ";
			default:
				return " ";
		}
	}
	
	public Tile clone()
	{
		Tile t = new Tile();
		t.setPositive(positive);
		t.setType(type);
		return t;
	}
}
