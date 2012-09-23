package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;
import java.util.LinkedList;

import android.util.Log;


public class GameState {
	public LinkedList<TileLayout> states;
	public LinkedList<RowGroup> states2;
	public static GameState saved;
	
	public GameState()
	{
		states = new LinkedList<TileLayout>();
		states.add(new TileLayout());
		states2 = new LinkedList<RowGroup>();
		states2.add(new RowGroup());
	}
	
	//start row group methods
	public boolean addTile(int row, int col, Tile t){
		//states2.peekLast().print();
		RowGroup r = states2.peekLast().clone();
		states2.add(r);
		boolean b = states2.peekLast().addTile(row, col, t);
		//states2.peekLast().print();
		if(states2.size() > 1)
		{
			//states2.get(states2.size()-2).print();
			if(states2.get(states2.size()-2).getRows().get(0) == states2.get(states2.size()-1).getRows().get(0)) Log.d("...", "#fail");
		}
		return b;
	}
	
	public LinkedList<Pos> updatePlusTiles(int row, int col){
		return states2.peekLast().updatePlusTiles(row, col);		
	}
	
	public ArrayList<Row> getRows(){
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
		Log.d("type", Tile.getSymbol(type));
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
	
	public GameState undo()
	{
		states2.peekLast().print();
		states.pop();
		states2.pop();
		states2.pop();
		states2.peekLast().print();
		return this;
	}
}
