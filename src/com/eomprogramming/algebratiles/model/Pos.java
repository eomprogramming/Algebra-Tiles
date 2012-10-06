package com.eomprogramming.algebratiles.model;

public class Pos {
	
	public int row, col;
	
	public Pos(int r, int c){
		setRow(r);
		setCol(c);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public Pos clone()
	{
		return new Pos(row, col);
	}
}
