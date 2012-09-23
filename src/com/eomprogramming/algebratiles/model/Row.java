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
	
	public Tile get(int i)
	{
		return tiles.get(i);
	}
	
	public Row clone()
	{
		Row r = new Row(position);
		ArrayList<Tile> t = new ArrayList<Tile>();
		for(Tile a: tiles)
			t.add(a.clone());
		r.tiles = t;
		return r;
	}
}
