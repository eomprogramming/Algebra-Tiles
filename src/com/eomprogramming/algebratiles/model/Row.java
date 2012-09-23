package com.eomprogramming.algebratiles.model;


import java.util.ArrayList;

public class Row {
	
	private ArrayList<Tile> tiles;
	private int position;
	
	public Row(int pos){
		tiles = new ArrayList<Tile>();
		setPosition(pos);
	}
	
	@SuppressWarnings("unchecked")
	public Row(int pos, ArrayList<Tile> tiles){
		this.tiles = (ArrayList<Tile>) tiles.clone();
		setPosition(pos);
	}
	
	public void add(Tile t){
		if(t != null)
			tiles.add(t);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int p) {
		position = p;
	}	
	
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	
	public Row clone()
	{
		return new Row(position, tiles);
	}
}
