package com.gravitygamesinteractive.byttstrikesback;

import java.awt.*;

public class Enemy extends Rectangle{
	private static final long serialVersionUID=1L;
	public int movementSpeed;
	public int dir;
	public int fallSpeed;
	public int jumpSpeed;
	public int ide;
	public int health;
	
	public Enemy(int x, int y, int width, int height, int ide){
		setBounds(x,y,width,height);
		
	}
	public void tick(){
		
	}

	public void render(Graphics g, Component c){
		
	}
	
}
