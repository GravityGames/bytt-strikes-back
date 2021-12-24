package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;

public class Hero {
	public static int animCount, animTime=5, animFrame=1;
	public static int deathUpCount,deathUpTime=10;
	public static boolean allowJump=false;
	public static boolean jumped=false, jumpedsound=false;
	public static int x=400;
	public static int y=300;
	public int startx;
	public static int getx,gety;
	public static int tiley;
	private Scanner charfile;
	public static int health=2;
	public static int maxhealth=3;
	public static int width=16,height=16,minsp=3,maxsp=6,accelsp=15,ability=1;
	public static int fallspeed = 5;
	public static int moveSpeed = 3;
	public static int jumpSpeed = 5;
	public static int jdecCount = 1;
	public static boolean invinciblehurt=false;
	public static boolean invinciblepower=false;
	public static int deathCount = 0;
	public static int deathTime = 125;
	public static int invincibleCount = 0;
	public static int invincibleTime = 125;
	public static int jumpSpeedcache = 5;
	public static int jumpHeight=30, jumpCount=0;
	public static int slopex, slopey, slopetemp;
	
	public static ImageIcon sproing1;
	public static ImageIcon sproing2;
	public static ImageIcon sproing3;
	public static ImageIcon sproing4;
	public static ImageIcon heart;
	public static ImageIcon heartempty;
	public static boolean isTouchingFloor;
	public static boolean canMove;
	public static boolean isJumping=false;
	public static boolean allowFall=true;
	public static boolean fallbypass=false;
	
	public Hero(int x, int y){
		this.x=x;
		this.startx=400;
		this.y=y;
		
			sproing1 = new ImageIcon("res/resources/images/Sproing/1.png");
			sproing2 = new ImageIcon("res/resources/images/Sproing/2.png");
			sproing3 = new ImageIcon("res/resources/images/Sproing/3.png");
			sproing4 = new ImageIcon("res/resources/images/Sproing/4.png");
		    heart = new ImageIcon("res/resources/images/Sproing/1.png");
			heartempty = new ImageIcon("res/resources/images/Sproing/4.png");
		    /*try{
		    	readCharAttributes();
		    }catch(Exception e){
		    	
		    }*/

	}
	
	public void tick(){
		if(health>0){
			if(!isCollidingWithWall(new Point(x+width,y), new Point(x+width+2,y+height-5))){
			x+=3;
			if(x>=Frame.gameSize.width/2 && Frame.sx<Level.maxx+16-Frame.gameSize.width){
			Frame.sx+=3;
			}else if(x>=Frame.gameSize.width/2){
				
			}
			}else{
				allowJump=true;
			}
			
			if(x>Level.maxx+16){	
				Level.levelOver=true;
					Level.levelWon=false;
				}
			
			if(!isCollidingWithFloor(new Point(x+width+8,y+height+8), new Point(x+width+8,y+height+8)) && !allowJump && x>=50 && !jumped){
				allowJump=true;
			}
			if(!isJumping && allowFall && !isCollidingWithFloor(new Point(x,y+height), new Point(x+16,y+height))){
				jumpCount=0;
				allowFall=true;
				if(fallspeed<5){
					if(jdecCount>=2){
						jdecCount=0;
						fallspeed+=1;
					}else{
						y+=fallspeed;
						if(Frame.sy>0 && Frame.sy<(Level.maxy+16-(Frame.gameSize.height))){
							Frame.sy+=fallspeed;
						}
						jdecCount+=1;
					}
					}else{
					y+=fallspeed;
					if(Frame.sy>0 && Frame.sy<(Level.maxy-(Frame.gameSize.height))){
						Frame.sy+=fallspeed;
					}else if(Frame.sy>=(Level.maxy-(Frame.gameSize.height))){
						Frame.sy=(Level.maxy-(Frame.gameSize.height));
					}
				    }
			}else{
				fallspeed=1;
				for(int e=0; e<Level.sprite.size(); e++){
					if(Level.sprite.get(e).ide==256){
					if(Level.sprite.get(e).x<=x+width+50 && Level.sprite.get(e).x>x && isCollidingWithFloor(new Point(x,y+height), new Point(x+16,y+height))){
						allowJump=true;
					}
					}
				}
				if(isJumping){	
					//isJumping=true;
					//System.out.println(true);
					allowFall=true;
				}else{
					jumped=false;
				jumpCount=0;	
				y=tiley-height;
				//allowFall=false;
				//jumpCount=0;
				}
			}
		if(invinciblehurt){
			if(invincibleCount>=invincibleTime){
				invincibleCount=0;
				invinciblehurt=false;
			}else{
				invincibleCount+=1;
				if(invincibleCount==1){
					playSoundDamage();
				}
			}
		}
		if(invinciblepower){
			if(invincibleCount>=invincibleTime){
				invincibleCount=0;
				invinciblehurt=false;
			}else{
				invincibleCount+=1;
			}
		}
		if(allowJump){
			jumped=true;
			isJumping=true;
			jump(10);
		}
	}else if(health<=0){
			if(deathCount>=deathTime){
				if(deathUpCount>=deathUpTime){
				allowFall=true;
				y+=3;
				if(y>=Level.maxy){
				Level.levelOver=true;
				Level.levelWon=true;
				}
				}else{
					y-=3;
					deathUpCount+=1;
				}
			}else{
				deathCount+=1;
			}
		}
	}
	
	private void jump(int jumpHeight) {
		if(jumpCount>=jumpHeight){
			if(jumpSpeed==0){
			isJumping=false;
			allowJump=false;
			Frame.isJumping=false;
			jumpCount=0;
			allowFall=true;
			jumpSpeed=5;
			}else{
				if(jdecCount>=2){
					jdecCount=0;
					jumpSpeed-=1;
				}else{
					y-=jumpSpeed;
					if(Frame.sy>0 && y-Frame.sy<(Frame.gameSize.height/2)){
						Frame.sy-=jumpSpeed;
					}
					jdecCount+=1;
				}
			}
		}else{
			if(!isCollidingWithCeiling(new Point(x,y),new Point(x+width,y))){
			y-=jumpSpeed;
			if(Frame.sy>0){
				Frame.sy-=jumpSpeed;
			}
			jumpCount+=1;
			if(jumpCount==1){
				playSound();
				jumpedsound=true;
			}
		}else{
			jumpSpeed=0;
			jumpCount=jumpHeight;
		}
		}
	}

	public boolean isCollidingWithFloor(Point pt1, Point pt2){
		if(deathCount>=deathTime){
			isTouchingFloor=false;
			return false;
		}else{
		for(int f=0;f<Level.tile.size();f++){
			if(Level.tile.get(f).contains(pt1) || Level.tile.get(f).contains(pt2)){
				if(Level.tile.get(f).id==1 || Level.tile.get(f).id==2 || Level.tile.get(f).id==6){
				tiley=Level.tile.get(f).y;
				isTouchingFloor=true;
				return true;
				}
				if(Level.tile.get(f).id==8){
					if(!invinciblehurt){
					if(health>1){
						tiley=Level.tile.get(f).y;
					isTouchingFloor=true;
					Frame.isJumping=true;
					KeyListen.downpressed=true;
					isJumping=true;
					jumpCount=0;
					allowFall=true;
					health-=1;
					invinciblehurt=true;
					return true;
					}else{
						tiley=Level.tile.get(f).y;
						isTouchingFloor=true;
						health-=1;
						return true;
					}
					}else{
						return true;
					}
					}
				/*if(Level.tile.get(f).id==9){
					if (Frame.dir>=0){
						tiley=Level.tile.get(f).y;
						isTouchingFloor=true;
						y=slopetemp;
						return false;
						}
						if (Frame.dir<0){
							//y=slopetemp;
							return false;
							}
						if (Frame.dir==0){
							//y=slopetemp;
							return true;
							}
				}*/
				if(Level.tile.get(f).id==9){
					slopex=Level.tile.get(f).x;
					slopey=Level.tile.get(f).y;
					tiley=slopex-x+slopey;
					isTouchingFloor=true;
					allowFall=false;
					return true;
				}
				if(Level.tile.get(f).id==10){
					slopex=Level.tile.get(f).x;
					slopey=Level.tile.get(f).y;
					tiley=x-slopex+slopey;
					isTouchingFloor=true;
					allowFall=false;
					return true;
				}
				if(Level.tile.get(f).id==11){
					slopex=Level.tile.get(f).x;
					slopey=Level.tile.get(f).y;
					int slopexdiv=slopex/2;
					//tiley=slopex-x+(slopey-y);
					tiley=((slopex-x)*2)/(slopey/2);
					isTouchingFloor=true;
					allowFall=false;
					return true;
				}
				
			}
			
		}
		isTouchingFloor=false;
		return false;
		}
	}
	
	public boolean isCollidingWithWall(Point pt1, Point pt2){
		for(int g=0;g<Level.tile.size();g++){
			if(Level.tile.get(g).contains(pt1) || Level.tile.get(g).contains(pt2)){
				if(Level.tile.get(g).id==1 || Level.tile.get(g).id==3 || Level.tile.get(g).id==6 || Level.tile.get(g).id==7 || Level.tile.get(g).id==8){
				return true;
				}
				if(Level.tile.get(g).id==9 || Level.tile.get(g).id==10 || Level.tile.get(g).id==11){
					/*if (Frame.dir>0){
					slopex=Level.tile.get(g).x;
					System.out.println(slopex);
					//slopey=y;
					System.out.println(x+16);
					slopetemp=y-(x+16-slopex);
					//System.out.println(slopetemp);*/
					//y=x-Level.tile.get(g).x+Level.tile.get(g).y+3;
					//slopex=Level.tile.get(g).x;
					//slopey=Level.tile.get(g).y;
					//tiley=slopex-x+slopey;
					//isTouchingFloor=true;
					//allowFall=false;
					y-=1;
					return false;
					/*}
					if (Frame.dir<0){
						slopex=Level.tile.get(g).x;
						//slopey=y;
						//x-=Frame.dir;
						//slopetemp=y+(x-slopex);
						System.out.println(slopetemp);
						//y+=Frame.dir;
						return false;
						}
					if (Frame.dir==0){
						
					}*/
				}
			}
		}
		return false;
	}
	
	public boolean isCollidingWithCeiling(Point pt1, Point pt2){
		if(deathCount>=deathTime){
			isTouchingFloor=false;
			return false;
		}else{
		for(int f=0;f<Level.tile.size();f++){
			if(Level.tile.get(f).contains(pt1) || Level.tile.get(f).contains(pt2)){
				if(Level.tile.get(f).id==1 || Level.tile.get(f).id==4 || Level.tile.get(f).id==7 || Level.tile.get(f).id==8 || Level.tile.get(f).id==10){
				return true;
				}	
			}
			
		}
		return false;
		}
	}
	
	public void render(Graphics g, Component c){
		if(invincibleCount % 2 == 0){
			//g.drawImage(kylegfx,x-8-Frame.sx,y-Frame.sy,null);
			
			if(animCount>=animTime){
				animCount=0;
				if(animFrame==2){
					animFrame=1;
				}else{
					animFrame+=1;
				}
			}else{
				animCount+=1;
			}
			
			if(animFrame==1 && !allowJump && health>0){
			sproing1.paintIcon(c,g,x-Frame.sx,y-Frame.sy);
			}
			if(animFrame==2 && !allowJump && health>0){
				sproing2.paintIcon(c,g,x-Frame.sx,y-Frame.sy);
				}
			if(allowJump && health>0){
					sproing3.paintIcon(c,g,x-Frame.sx,y-Frame.sy);
			}
			}else{
				
			}
			if(health<=0){
				sproing4.paintIcon(c,g,x-Frame.sx,y-Frame.sy);
				//g.fillRect(2, 2, 16, 16);
				//g.drawImage(heartempty,2,2,null);
				heartempty.paintIcon(c,g,2,2);
				heartempty.paintIcon(c,g,18,2);
				heartempty.paintIcon(c,g,34,2);
			}
			if(health==1){
				heart.paintIcon(c,g,2,2);
				heartempty.paintIcon(c,g,18,2);
				heartempty.paintIcon(c,g,34,2);
			}
			if(health==2){
				heart.paintIcon(c,g,2,2);
				heart.paintIcon(c,g,18,2);
				heartempty.paintIcon(c,g,34,2);
			}
			if(health==3){
				heart.paintIcon(c,g,2,2);
				heart.paintIcon(c,g,18,2);
				heart.paintIcon(c,g,34,2);
			}
	}
	
	private void playSound(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Jump.wav")); 
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
	        clip.open(spawnsound);
	        
	        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        control.setValue(20f * (float) Math.log10(Main.soundVolume));
	        
	        clip.loop(0);
	        /*if(jumpedsound=true){
	        clip.close();
	        }*/
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
	private void playSoundDamage(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Damage.wav")); 
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
	        clip.open(spawnsound);
	        
	        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        control.setValue(20f * (float) Math.log10(Main.soundVolume));
	        
	        clip.loop(0);
	        /*if(jumpedsound=true){
	        clip.close();
	        }*/
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
	public void readCharAttributes(){
		try{
			String character = new String("assets/KyleAttributes.txt");
			charfile = new Scanner(new File(character));
			while(charfile.hasNextLine()){
				width=charfile.nextInt();
				height=charfile.nextInt();
				height=16;
				minsp=charfile.nextInt();
				maxsp=charfile.nextInt();
				accelsp=charfile.nextInt();
				jumpHeight=charfile.nextInt();
				ability=charfile.nextInt();
				System.out.println(width);
				System.out.println(height);
				System.out.println(minsp);
				System.out.println(maxsp);
				System.out.println(accelsp);
				System.out.println(jumpHeight);
				System.out.println(ability);
	}
			charfile.close();
	}catch(Exception e){
		System.out.println("error");
	}
	}
}
