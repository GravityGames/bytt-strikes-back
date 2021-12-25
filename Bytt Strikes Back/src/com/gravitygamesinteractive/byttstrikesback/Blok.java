package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Blok extends Enemy{
	public static ImageIcon blok1;
	public static ImageIcon blok2;
	public static ImageIcon blok3;
	public static ImageIcon blok4;
	public int animCount, animTime=90;
	public int tiley,jdecCount=0;
	public boolean allowFall;
	
	public Blok(int x, int y, int width, int height, int ide){
		super(x,y,16,16,256);
		this.ide=ide;
		blok1 = new ImageIcon("res/resources/images/Blok/1.png");
		blok2 = new ImageIcon("res/resources/images/Blok/2.png");
		blok3 = new ImageIcon("res/resources/images/Blok/3.png");
		blok4 = new ImageIcon("res/resources/images/Blok/4.png");
	}
	
	public void tick(){
		super.tick();
		if(!allowFall&&!isCollidingWithFloor(new Point(x,(y+height)),new Point((x+width),(y+height)))){
		if(fallSpeed<2){
			if(jdecCount>=2){
				jdecCount=0;
				fallSpeed+=1;
			}else{
				y+=fallSpeed;
				if(Level.scrollY>0){
					Level.scrollY+=fallSpeed;
				}
				jdecCount+=1;
			}
	}else{
		y+=fallSpeed;
	}
	}else{
		fallSpeed=1;
		y=tiley-height;
		allowFall=false;
	}
		if(Hero.x+Hero.width>=x&&Hero.x+Hero.width<=x+width&&Hero.y>=y&&Hero.y<=y+height){
			if(!Hero.invinciblehurt && !Hero.invinciblepower){
			Hero.health-=1;
			//System.out.println(Hero.health);
			Hero.invinciblehurt=true;
			}else if(Hero.invinciblepower){
				health-=1;
			}
		}
	}
	
	public void render(Graphics g, Component c){
		if(animCount>=animTime){
			animCount=0;
		}else{
			animCount+=1;
		}
		if(animCount<=50){
		 blok1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		}
		if(animCount>50 && animCount<=60){
			blok2.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		}
if(animCount>60 && animCount<=70){
	blok4.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		}
if(animCount>70 && animCount<=80){
	blok3.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
}
if(animCount>80 && animCount<=90){
	blok4.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
}
	}
	
	public boolean isCollidingWithFloor(Point pt1, Point pt2){
		for(int f=0;f<Level.tile.size();f++){
			if(Level.tile.get(f).contains(pt1) || Level.tile.get(f).contains(pt2)){
				if(Level.tile.get(f).id==1 || Level.tile.get(f).id==2 || Level.tile.get(f).id==6){
				tiley=Level.tile.get(f).y;
				//isTouchingFloor=true;
				return true;
				}
				if(Level.tile.get(f).id==8){
					//if(health>1){
						tiley=Level.tile.get(f).y;
					//isTouchingFloor=true;
					//Frame.isJumping=true;
					//KeyListen.downpressed=true;
					//isJumping=true;
					//jumpCount=0;
					//allowFall=true;
					health-=1;
					return true;
					//}else{
						//tiley=Level.tile.get(f).y;
						//isTouchingFloor=true;
						//health-=1;
						//return true;
					//}
					}
				if(Level.tile.get(f).id==9){
					if (Level.dir>=0){
						//y=slopetemp;
						return false;
						}
						if (Level.dir<0){
							//y=slopetemp;
							return false;
							}
				}
				
				
			}
		}
			
		//isTouchingFloor=false;
		return false;
	}

}
