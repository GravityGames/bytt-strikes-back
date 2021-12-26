package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Tile{
	public int id, priorety, x, y, width, height;
	public int TileId;

	public static final int TILE_SIZE = 16;

	public static Image tileset1;
	
	public Tile(int id,int x, int y, int TileId, int priorety){
		this.id=id;
		//this.id=0;
		this.x=x;
		this.y=y;
		this.TileId=TileId;
		this.priorety=priorety;
		this.width=16;
		this.height=16;
	}

	public void tick(){

	}
	
	public boolean contains(Point point) {
		if(point.x >= x && point.x <= x+TILE_SIZE && point.y >= y && point.y <= y + TILE_SIZE) {
			return true;
		}
		return false;
	}

	public void render(Graphics g, Component c){
		//g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, ((TileId-1)%16)*TILE_SIZE, ((TileId-1)/16)*TILE_SIZE, (((TileId-1)%16)+1)*TILE_SIZE, (((TileId-1)/16)+1)*TILE_SIZE, null);
	}
}
