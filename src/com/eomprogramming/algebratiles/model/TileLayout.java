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
				addRow(row, Tile.ONE);
				addCol(col, Tile.X);
			}
			else
			{
				addRow(row, Tile.X);
				addCol(col, Tile.ONE);
			}
		}
		else if(type == Tile.X_SQUARED)
		{
			addRow(row, Tile.X);
			addCol(col, Tile.X);
		}
		else
		{
			addRow(row, Tile.ONE);
			addCol(col, Tile.ONE);
		}
		if(row == rowSign.size() && col == colSign.size())
		{
			rowSign.add(true);
			colSign.add(isPositive);
		}
		else if(row == rowSign.size())
			rowSign.add(!isPositive ^ colSign.get(col));
		else if(col == colSign.size())
			colSign.add(!isPositive ^ rowSign.get(row));
	}
	
	public void addRow(int row, int type)
	{
		if(row == rowType.size())
			rowType.add(type);
	}
	
	public void addCol(int col, int type)
	{
		if(col == colType.size())
			colType.add(type);
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		if(row < rowType.size() && col < colType.size())
		{
			if(rowSign.get(row) ^ colSign.get(col) ^ !isPositive)
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
			return false;
		}
		if(col < colType.size())
		{
			if(type == Tile.X)
				return true;
			if(type == Tile.X_SQUARED && colType.get(col) == Tile.X)
				return true;
			if(type == Tile.ONE && colType.get(col) == Tile.ONE)
				return true;
			return false;
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
