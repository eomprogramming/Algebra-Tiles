package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
	public LinkedList<TileLayout> states;
	public LinkedList<RowGroup> states2;
	
	public GameState()
	{
		states = new LinkedList<TileLayout>();
		states.add(new TileLayout());
		states2 = new LinkedList<RowGroup>();
		states2.add(new RowGroup());
	}
	
	//start row group methods
	public boolean addTile(int row, int col, Tile t){
		RowGroup r = states2.peekLast().clone();
		boolean b = r.addTile(row, col, t);
		states2.add(r);
		return b;
	}
	
	public LinkedList<Pos> updatePlusTiles(int row, int col){
		return states2.peekLast().updatePlusTiles(row, col);		
	}
	
	public LinkedList<Row> getRows(){
		return states2.peekLast().getRows();
	}
	//end row group methods
	
	public int getPrevNumRows()
	{
		return states.peekLast().getPrevNumRows();
	}
	
	public int getPrevNumCols()
	{
		return states.peekLast().getPrevNumCols();
	}
	
	public ArrayList<Boolean> getRowSign()
	{
		return states.peekLast().rowSign;
	}
	
	public ArrayList<Boolean> getColSign()
	{
		return states.peekLast().colSign;
	}
	
	public ArrayList<Integer> getRowType()
	{
		return states.peekLast().rowType;
	}
	
	public ArrayList<Integer> getColType()
	{
		return states.peekLast().colType;
	}
	
	public void add(int row, int col, int type, boolean isPositive)
	{
		TileLayout t = states.peekLast().clone();
		t.add(row, col, type, isPositive);
		states.add(t);
	}
	
	public boolean isValid(int row, int col, int type, boolean isPositive)
	{
		return states.peekLast().isValid(row, col, type, isPositive);
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return should the X tile be placed horizontally
	 */
	public boolean isHorizontal(int row, int col)
	{
		return states.peekLast().isHorizontal(row, col);
	}
	
	public void undo()
	{
		states.pop();
		states2.pop();
	}
}
