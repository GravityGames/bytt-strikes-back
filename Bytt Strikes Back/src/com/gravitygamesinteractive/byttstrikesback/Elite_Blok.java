package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Elite_Blok extends Enemy{
	public int shootTime=100,shootCount;
	public static boolean shootingPrepared,shotOnce=false;
	public static ImageIcon eblok1;
	public static ImageIcon eblok2;
	public static ImageIcon eblok3;
	public static ImageIcon eblok4;
	public int tiley,jdecCount=0;
	public boolean allowFall;
	
	public Elite_Blok(int x, int y, int width, int height, int ide){
		super(x,y,16,20,256);
		this.ide=ide;
		eblok1 = new ImageIcon("res/resources/images/EliteBlok/1.png");
		eblok2 = new ImageIcon("res/resources/images/EliteBlok/2.png");
		eblok3 = new ImageIcon("res/resources/images/EliteBlok/3.png");
		eblok4 = new ImageIcon("res/resources/images/EliteBlok/4.png");
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
			System.out.println(Hero.health);
			Hero.invinciblehurt=true;
			}else if(Hero.invinciblepower){
				health-=1;
			}
		}
		
		if(Hero.y>=y-32&&Hero.y<=y+height+32 && Hero.health>0){
			shootingPrepared=true;
		}else{
			shootingPrepared=false;
			shootCount=0;
			shotOnce=false;
		}
		
		if(shootingPrepared){
			if(shootCount>=shootTime){
				shotOnce=true;
				if(shootCount==shootTime){
				Level.sprite.add(new Bullet(x,y+15,8,8,258));
				}
				if(shootCount==100){
					playSound();
				}
				if(shootCount==140){
				shootCount=40;
				}else{
					shootCount+=1;
				}
			}else{
				shootCount+=1;
			}
		}
	}
	
	private void playSound(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Bullet.wav")); 
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
	        clip.open(spawnsound);
	        clip.loop(0);
	        /*if(spawnSoundPlaying=false){
	        clip.close();
	        }*/
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
	
	public void render(Graphics g, Component c){
		if(shootCount<95 && !shotOnce) {
		eblok1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		}
		 
		 if(shootCount>=85 && shootCount<95){
			 eblok2.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		 }
		 if (shootCount>=95){
			 eblok3.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
		 }
		 if (shootCount<95 && shotOnce){
			 eblok4.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY);
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
