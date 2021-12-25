package com.gravitygamesinteractive.byttstrikesback;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.ImageIcon;

import java.io.*;

public class Tile extends Rectangle{
	private static final long serialVersionUID=1L;
	public int id,priorety;
	public int TileId;
	
	public static int[] g1top={0,0};
	public static Image tileset1;
	public static ImageIcon tileset1groundlefttop;
	public static ImageIcon tileset1groundtop;
	public static ImageIcon tileset1groundrighttop;
	public static ImageIcon tileset1groundleft;
	public static ImageIcon tileset1ground;
	public static ImageIcon tileset1groundright;
	public static ImageIcon tileset1groundbotleft;
	public static ImageIcon tileset1groundbot;
	public static ImageIcon tileset1groundbotright;
	public static ImageIcon tileset1spike1;
	public static ImageIcon tileset1deco1;
	public static ImageIcon tileset1deco2;
	public static ImageIcon tileset1deco3;
	public static ImageIcon tileset1deco4;
	public static ImageIcon tileset1deco5;
	public Tile(int id,int x, int y, int TileId, int priorety){
		this.id=id;
		this.x=x;
		this.y=y;
		this.TileId=TileId;
		this.priorety=priorety;
		this.width=16;
		this.height=16;
			tileset1groundlefttop = new ImageIcon("res/resources/images/tileset1_1.png");
			tileset1groundtop = new ImageIcon("res/resources/images/tileset1_2.png");
			tileset1groundrighttop = new ImageIcon("res/resources/images/tileset1_3.png");
		    tileset1groundleft = new ImageIcon("res/resources/images/tileset1_4.png");
		    tileset1ground = new ImageIcon("res/resources/images/tileset1_5.png");
		    tileset1groundright = new ImageIcon("res/resources/images/tileset1_6.png");
		    tileset1groundbotleft =  new ImageIcon("res/resources/images/tileset1_7.png");
		    tileset1groundbot = new ImageIcon("res/resources/images/tileset1_8.png");
		    tileset1groundbotright = new ImageIcon("res/resources/images/tileset1_9.png");
		    tileset1spike1= new ImageIcon("res/resources/images/tileset1_19.png");
		    tileset1deco1= new ImageIcon("res/resources/images/tileset1_45.png");
		    tileset1deco2= new ImageIcon("res/resources/images/tileset1_46.png");
		    tileset1deco3= new ImageIcon("res/resources/images/tileset1_47.png");
		    tileset1deco4= new ImageIcon("res/resources/images/tileset1_48.png");
		    tileset1deco5= new ImageIcon("res/resources/images/tileset1_49.png");
	}
	
public void tick(){
	if(!Character.isJumping && !Character.isTouchingFloor){
		//y=y-Character.fallspeed;
		//this.y=y-Character.fallspeed;
	}
	if(Character.isJumping){
		if(Character.jumpCount>=Character.jumpHeight){
			//y=y-Character.fallspeed;
			//this.y=y-Character.fallspeed;
		}else{
		//y=y+Character.fallspeed;
		//this.y=y+Character.fallspeed;
		}
	}
	//x=x-Frame.dir;
	//this.x=x-Frame.dir;
	//System.out.println(this.x);
	//System.out.println(this.y);
}
	
	public void render(Graphics g, Component c){
		g.setColor(Color.red);
		if (TileId==1){
		//tileset1groundlefttop.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 0, 0, 16, 16, null);
		}
		if (TileId==2){
			//g.fillRect(x-Level.scrollX,y-Level.scrollY,16,16);
			//tileset1groundtop.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 16, 0, 32, 16, null);
		}
		if (TileId==3){
			//tileset1groundrighttop.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 32, 0, 48, 16, null);
		}
		if (TileId==4){
			//tileset1groundleft.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 0, 16, 16, 32, null);
		}
		if (TileId==5){
			//tileset1ground.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 16, 16, 32, 32, null);
		}
		if (TileId==6){
			//tileset1groundright.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 32, 16, 48, 32, null);
		}
		if (TileId==7){
			//tileset1groundbotleft.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 0, 32, 16, 48, null);
		}
		if (TileId==8){
			//tileset1groundbot.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 16, 32, 32, 48, null);
		}
		if (TileId==9){
			//tileset1groundbotright.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 32, 32, 48, 48, null);
		}
		if (TileId==19){
			//tileset1spike1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 48, 0, 64, 16, null);
		}
		if (TileId==45){
			//tileset1deco1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 64, 0, 80, 16, null);
		}
		if (TileId==46){
			//tileset1deco2.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 80, 0, 96, 16, null);
		}
		if (TileId==47){
			//tileset1deco3.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 96, 0, 112, 16, null);
		}
		if (TileId==48){
			//tileset1deco4.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 112, 0, 128, 16, null);
		}
		if (TileId==49){
			//tileset1deco5.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
			g.drawImage(tileset1, x-Level.scrollX, y-Level.scrollY, x+16-Level.scrollX, y+16-Level.scrollY, 128, 0, 144, 16, null);
		}
	}
}
