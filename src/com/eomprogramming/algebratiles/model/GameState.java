package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;
import java.util.LinkedList;

import com.eomprogramming.algebratiles.datastructure.Stack;
import com.eomprogramming.algebratiles.math.QEquation;

import android.util.Log;


public class GameState {
	public TileLayout tileLayout;
	public RowGroup rowGroup;
	public Stack<Pos> moves;
	public static QEquation q;
	
	public GameState()
	{
		tileLayout = new TileLayout();
		rowGroup = new RowGroup();
		moves = new Stack<Pos>();
	}
	
	//start row group methods
	public boolean addTile(int row, int col, Tile t){
		return rowGroup.addTile(row, col, t);
	}
	
	public LinkedList<Pos> updatePlusTiles(int row, int col){
		return rowGroup.updatePlusTiles(row, col);		
	}
	
	public ArrayList<Row> getRows(){
		return rowGroup.getRows();
	}
	//end row group methods
	
	public int getPrevNumRows()
	{
		return tileLayout.getPrevNumRows();
	}
	
	public int getPrevNumCols()
	{
		return tileLayout.getPrevNumCols();
	}
	
	public ArrayList<Boolean> getRowSign()
	{
		return tileLayout.rowSign;
	}
	
	public ArrayList<Boolean> getColSign()
	{
		return tileLayout.colSign;
	}
	
	public ArrayList<Integer> getRowType()
	{
		return tileLayout.rowType;
	}
	
	public ArrayList<Integer> getColType()
	{
		return tileLayout.colType;
	}
	
	//from tile layout
	public void add(int row, int col, int type, boolean isPositive)
	{
		moves.push(new Pos(row, col));
		tileLayout.add(row, col, type, isPositive);
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		return tileLayout.isValid(row, col, type, isPositive);
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return should the X tile be placed horizontally
	 */
	public boolean isHorizontal(int row, int col)
	{
		return tileLayout.isHorizontal(row, col);
	}
}
