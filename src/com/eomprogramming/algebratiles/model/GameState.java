package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;
import java.util.LinkedList;

import com.eomprogramming.algebratiles.datastructure.Stack;


public class GameState {
	public Stack<TileLayout> states;
	public Stack<RowGroup> states2;
	
	public GameState()
	{
		states = new Stack<TileLayout>();
		states.push(new TileLayout());
		states2 = new Stack<RowGroup>();
		states2.push(new RowGroup());
	}
	
	//start row group methods
	public boolean addTile(int row, int col, Tile t){
		RowGroup r = states2.peek().clone();
		boolean b = r.addTile(row, col, t);
		states2.push(r);
		return b;
	}
	
	public LinkedList<Pos> updatePlusTiles(int row, int col){
		return states2.peek().updatePlusTiles(row, col);		
	}
	
	public ArrayList<Row> getRows(){
		return states2.peek().getRows();
	}
	//end row group methods
	
	public int getPrevNumRows()
	{
		return states.peek().getPrevNumRows();
	}
	
	public int getPrevNumCols()
	{
		return states.peek().getPrevNumCols();
	}
	
	public ArrayList<Boolean> getRowSign()
	{
		return states.peek().rowSign;
	}
	
	public ArrayList<Boolean> getColSign()
	{
		return states.peek().colSign;
	}
	
	public ArrayList<Integer> getRowType()
	{
		return states.peek().rowType;
	}
	
	public ArrayList<Integer> getColType()
	{
		return states.peek().colType;
	}
	
	public void add(int row, int col, int type, boolean isPositive)
	{
		TileLayout t = states.peek().clone();
		t.add(row, col, type, isPositive);
		states.push(t);
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		return states.peek().isValid(row, col, type, isPositive);
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return should the X tile be placed horizontally
	 */
	public boolean isHorizontal(int row, int col)
	{
		return states.peek().isHorizontal(row, col);
	}
	
	public void undo()
	{
		states.pop();
		states2.pop();
	}
}
