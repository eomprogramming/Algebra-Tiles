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
	
}
