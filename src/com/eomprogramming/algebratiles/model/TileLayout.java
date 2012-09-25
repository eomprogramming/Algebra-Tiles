package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;

public class TileLayout {
	public ArrayList<Integer> rowType; //1 or x
	public ArrayList<Boolean> rowSign; //positive or negative
	
	public ArrayList<Integer> colType; //1 or x
	public ArrayList<Boolean> colSign; //positive or negative
	
	public int isTopPositive; //if the top row contains positive x tiles
	public int isLeftPositive; //if the left row contains positive x tiles
	
	private int prevNumRows;
	private int prevNumCols;
	
	public static int POSITIVE = 1;
	public static int NEGATIVE = 0;
	public static int EMPTY = -1;
	
	public TileLayout()
	{
		rowType = new ArrayList<Integer>();
		rowSign = new ArrayList<Boolean>();
		colType = new ArrayList<Integer>();
		colSign = new ArrayList<Boolean>();
		
		prevNumRows = 0;
		prevNumCols = 0;
		
		isTopPositive = EMPTY; //if the top row contains positive x tiles
		isLeftPositive = EMPTY; //if the left row contains positive x tiles
	}
	
	public ArrayList<Boolean> getRowSign()
	{
		return rowSign;
	}
	
	public ArrayList<Boolean> getColSign()
	{
		return colSign;
	}
	
	public ArrayList<Integer> getRowType()
	{
		return rowType;
	}
	
	public ArrayList<Integer> getColType()
	{
		return colType;
	}
	
	public int getPrevNumRows()
	{
		return prevNumRows;
	}
	
	public int getPrevNumCols()
	{
		return prevNumCols;
	}
	
	public void add(int row, int col, int type, boolean isPositive)
	{
		if(type == Tile.X)
		{
			if(isHorizontal(row, col))
			{
				if(row == rowType.size() && isLeftPositive == EMPTY)
				{
					if(isPositive)
						isLeftPositive = POSITIVE;
					else
						isLeftPositive = NEGATIVE;
				}
				addRow(row, Tile.ONE);
				addCol(col, Tile.X);
			}
			else
			{
				if(col == colType.size() && isTopPositive == EMPTY)
				{
					if(isPositive)
						isTopPositive = POSITIVE;
					else
						isTopPositive = NEGATIVE;
				}
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
			if(row == rowType.size() && isLeftPositive == EMPTY)
			{
				if(isPositive)
					isLeftPositive = POSITIVE;
				else
					isLeftPositive = NEGATIVE;
			}
			if(col == colType.size() && isTopPositive == EMPTY)
			{
				if(isPositive ^ isLeftPositive == NEGATIVE)
					isTopPositive = POSITIVE;
				else
					isTopPositive = NEGATIVE;
			}
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
		{
			rowType.add(type);
			prevNumRows = rowType.size()-1;
		}
	}
	
	public void addCol(int col, int type)
	{
		if(col == colType.size())
		{
			colType.add(type);
			prevNumCols = colType.size()-1;
		}
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		if(type == Tile.X)
		{
			if(isHorizontal(row, col))
			{
				if(!(isLeftPositive == EMPTY) && isLeftPositive == POSITIVE ^ isPositive)
						return false;
			}
			else
			{
				if(!(isTopPositive == EMPTY) && isTopPositive == POSITIVE ^ isPositive)
						return false;
			}
		}
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
			if(type == Tile.ONE && rowType.get(row) == Tile.ONE && (isLeftPositive != NEGATIVE ^ isTopPositive != NEGATIVE ^ isPositive))
				return true;
			return false;
		}
		if(col < colType.size())
		{
			if(type == Tile.X)
				return true;
			if(type == Tile.X_SQUARED && colType.get(col) == Tile.X)
				return true;
			if(type == Tile.ONE && colType.get(col) == Tile.ONE && (isLeftPositive != NEGATIVE ^ isTopPositive != NEGATIVE ^ isPositive))
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
	
	@SuppressWarnings("unchecked")
	public TileLayout clone()
	{
		TileLayout t = new TileLayout();
		t.rowType = (ArrayList<Integer>) rowType.clone();
		t.rowSign = (ArrayList<Boolean>) rowSign.clone();
		t.colType = (ArrayList<Integer>) colType.clone();
		t.colSign = (ArrayList<Boolean>) colSign.clone();
		
		t.prevNumRows = prevNumRows;
		t.prevNumCols = prevNumCols;
		
		t.isTopPositive = isTopPositive;
		t.isLeftPositive = isLeftPositive;
		
		return t;
	}
	
	
}
