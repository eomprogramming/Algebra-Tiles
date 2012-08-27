package com.eomprogramming.algebratiles.model;


import java.util.LinkedList;

public class Row {
	
	private LinkedList<Tile> tiles;
	private int position;
	
	public Row(int pos){
		tiles = new LinkedList<Tile>();
		setPosition(pos);
	}
	
	public Row(int pos, LinkedList<Tile> tiles){
		this.tiles = tiles;
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
	
	public LinkedList<Tile> getTiles(){
		return tiles;
	}
}
