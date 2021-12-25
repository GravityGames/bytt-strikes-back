package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet extends Enemy{
	
	public static ImageIcon bullet;
	public static int xSpeed=-5;
	
	public Bullet(int x, int y, int width, int height, int ide){
		super(x,y,8,8,258);
		this.ide=ide;
		bullet = new ImageIcon("res/resources/images/EliteBlok/Bullet.png");
	}
	
	public void tick(){
		x+=xSpeed;
		if(x<=Hero.x+Hero.width&&x>=Hero.x&&y+height>=Hero.y&&y<=Hero.y+Hero.height){
			if(!Hero.invinciblehurt && !Hero.invinciblepower){
			Hero.health-=1;
			System.out.println(Hero.health);
			Hero.invinciblehurt=true;
			}else if(Hero.invinciblepower){
				health-=1;
			}
		}
	}
	
	public void render(Graphics g, Component c){
		 bullet.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
	}

}
