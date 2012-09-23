package com.eomprogramming.algebratiles.model;

import java.util.ArrayList;
import java.util.LinkedList;

import android.util.Log;

public class RowGroup {
	private ArrayList<Row> rows;
	
	public RowGroup(){
		rows = new ArrayList<Row>();
		Row firstRow = new Row(0);
		firstRow.add(new Tile(Tile.PLUS, true));
		rows.add(firstRow);
	//	print();
	}
	
	public boolean addTile(int row, int col, Tile t){
		if(rows.size() <= row || rows.get(row).getTiles().size() <= col || rows.get(row).getTiles().get(col).getType() != Tile.PLUS)
			return false;
		rows.get(row).getTiles().set(col, t);
		//updatePlusTiles(row,col);
		return true;
	}
	
	public LinkedList<Pos> updatePlusTiles(int row, int col){
		
		LinkedList<Pos> positions = new LinkedList<Pos>();
		//Add a plus after it
		if(rows.get(row).getTiles().size() == (col+1)){
			rows.get(row).add(new Tile(Tile.PLUS,true));			
			positions.add(new Pos(row, col+1));
		}else if(rows.get(row).getTiles().get(col+1).getType() == Tile.EMPTY){
			rows.get(row).getTiles().set((col+1), new Tile(Tile.PLUS,true));
			positions.add(new Pos(row, col+1));
		}
		
		//Add a plus before it (to its left)
		if(col != 0 && rows.get(row).getTiles().get(col-1).getType() == Tile.EMPTY){
			rows.get(row).getTiles().set((col-1), new Tile(Tile.PLUS,true));
			positions.add(new Pos(row, col-1));
		}
		
		//If it's the last row, add a plus below it on a new row
		if(rows.size() == (row + 1)){
			Row newRow = new Row(rows.size());
			for(int i=0;i<col;i++)
				newRow.add(new Tile(Tile.EMPTY,true));
			newRow.add(new Tile(Tile.PLUS,true));
			rows.add(newRow);			
			positions.add(new Pos(row+1, col));
			
		}else{
			//It isn't the last row, add a plus below it
			if(rows.get(row+1).getTiles().size() > (col)){				
				if(rows.get(row+1).getTiles().get(col).getType() == Tile.EMPTY){
					rows.get(row+1).getTiles().set(col, new Tile(Tile.PLUS,true));
					positions.add(new Pos(row+1, col));
				}
			}else{
				for(int i=rows.get(row+1).getTiles().size();i<col;i++)
					rows.get(row+1).add(new Tile(Tile.EMPTY,true));
				rows.get(row+1).add(new Tile(Tile.PLUS,true));	
				positions.add(new Pos(row+1, col));
			}			
		}		
		
		//Add a plus above it if it isn't the top row
		if(row !=0){
			if(rows.get(row-1).getTiles().size() <= col){
				for(int i=rows.get(row-1).getTiles().size();i<col;i++)
					rows.get(row-1).add(new Tile(Tile.EMPTY,true));
				rows.get(row-1).add(new Tile(Tile.PLUS,true));		
				positions.add(new Pos(row-1, col));
			}else if(rows.get(row-1).getTiles().get(col).getType() == Tile.EMPTY){
				rows.get(row-1).getTiles().set(col, new Tile(Tile.PLUS,true));
				positions.add(new Pos(row-1, col));
			}
		}
		//print();			
		return positions;		
	}
	
	public ArrayList<Row> getRows(){
		return rows;
	}
	
	@SuppressWarnings("unchecked")
	public RowGroup clone()
	{
		RowGroup r = new RowGroup();
		r.rows = (ArrayList<Row>) rows.clone();
		return r;
	}
	
	public void print(){
		for(Row r: rows){
			String line = "";
			for(Tile t : r.getTiles()){
				line+=t.getSymbol();
			}
			Log.d("a-t", line);
		}
		Log.d("a-t", "-----------------------------");
	}
	
	public static void print(ArrayList<Row> rows){
		for(Row r: rows){
			String line = "";
			for(Tile t : r.getTiles()){
				line+=t.getSymbol();
			}
			Log.d("a-t", line);
		}
		Log.d("a-t", "-----------------------------");
	}
	
}
