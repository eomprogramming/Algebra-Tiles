package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;

public class TileLayout {
	ArrayList<Integer> rowType; //1 or x
	ArrayList<Boolean> rowSign; //positive or negative
	
	ArrayList<Integer> colType; //1 or x
	ArrayList<Boolean> colSign; //positive or negative
	
	public TileLayout()
	{
		rowType = new ArrayList<Integer>();
		rowSign = new ArrayList<Boolean>();
		colType = new ArrayList<Integer>();
		colSign = new ArrayList<Boolean>();
	}
	
	public void add(int row, int col, int type, boolean isPositive)
	{
		if(type == Tile.X)
		{
			if(isHorizontal(row, col))
			{
				addRow(row, Tile.ONE, isPositive);
				addCol(col, Tile.X, isPositive);
			}
			else
			{
				addRow(row, Tile.X, isPositive);
				addCol(col, Tile.ONE, isPositive);
			}
		}
		else if(type == Tile.X_SQUARED)
		{
			addRow(row, Tile.X, isPositive);
			addCol(col, Tile.X, isPositive);
		}
		else
		{
			addRow(row, Tile.ONE, isPositive);
			addCol(col, Tile.ONE, isPositive);
		}
	}
	
	public void addRow(int row, int type, boolean isPositive)
	{
		if(row == rowType.size())
		{
			rowType.add(type);
			rowSign.add(isPositive);
		}
	}
	
	public void addCol(int col, int type, boolean isPositive)
	{
		if(col == colType.size())
		{
			colType.add(type);
			colSign.add(isPositive);
		}
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		if(row < rowType.size() && col < colType.size())
		{
			if(rowSign.get(row) && colSign.get(col) && !isPositive)
				return false;
			if(rowType.get(row) == Tile.X && colType.get(col) == Tile.X)
				return type == Tile.X_SQUARED;
			else if(rowType.get(row) == Tile.ONE && colType.get(col) == Tile.ONE)
				return type == Tile.ONE;
			else
				return type == Tile.X;
		}
		if(row < rowType.size())
		{
			if(type == Tile.X)
				return true;
			if(type == Tile.X_SQUARED && rowType.get(row) == Tile.X)
				return true;
			if(type == Tile.ONE && rowType.get(row) == Tile.ONE)
				return true;
		}
		if(col < colType.size())
		{
			if(type == Tile.X)
				return true;
			if(type == Tile.X_SQUARED && colType.get(col) == Tile.X)
				return true;
			if(type == Tile.ONE && colType.get(col) == Tile.ONE)
				return true;
		}
		return true;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return should the X tile be placed horizontally
	 */
	public boolean isHorizontal(int row, int col)
	{
		if(row < rowType.size())
			return rowType.get(row) == Tile.ONE;
		if(col < colType.size())
			return colType.get(col) == Tile.X;
		return true;
	}
}
