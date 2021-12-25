package com.gravitygamesinteractive.byttstrikesback;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
//import sun.audio.*;


public class Character {
	public static int animCount, animTime=5, animFrame=1;
	public static int x=400;
	public static int y=300;
	public int startx;
	//public static int getx,gety;
	public static int tiley;
	private Scanner charfile;
	public boolean up = false, down = false, left = false, right = false;
	public static boolean movingup,movingdown,spawningEnemy,spawnSoundPlaying=true,spaceDown;
	public static int spawnCount,spawnTime=1;
	public static int health=3;
	public static int maxhealth=3;
	public static int width=16,height=45,minsp=3,maxsp=6,accelsp=15,ability=1;
	public static int fallspeed = 5;
	public static int moveSpeed = 3;
	public static int jumpSpeed = 5;
	public static int verticalMoveSpeed = 3;
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

	public static ImageIcon base;
	public static ImageIcon hand1;
	public static ImageIcon hand2;
	public static ImageIcon feet;
	public static ImageIcon hoverboard;
	public static ImageIcon fire;
	public static ImageIcon blok;
	public static ImageIcon eliteblok;
	public static boolean isTouchingFloor;
	public static boolean canMove;
	public static boolean isJumping=false;
	public static boolean allowFall=true;
	public static boolean fallbypass=false;

	public Character(int x, int y){
		this.x=x;
		this.startx=400;
		this.y=y;

		base = new ImageIcon("res/resources/images/Bytt/Base.png");
		hand1 = new ImageIcon("res/resources/images/Bytt/Hand1.png");
		hand2 = new ImageIcon("res/resources/images/Bytt/Hand2.png");
		feet = new ImageIcon("res/resources/images/Bytt/Feet.png");
		hoverboard = new ImageIcon("res/resources/images/Bytt/Hoverboard.png");
		fire = new ImageIcon("res/resources/images/Bytt/Fire.png");
		blok = new ImageIcon("res/resources/images/Blok/1.png");
		eliteblok = new ImageIcon("res/resources/images/EliteBlok/1.png");
		try{
			readCharAttributes();
		}catch(Exception e){

		}

	}

	public void input() {
		if(!Level.levelOver) {
			if(KeyListen.input[8] && !KeyListen.input[9]) {
				left = true;
				right = false;
				//Frame.isMoving=true;
				//Frame.dir=-Character.moveSpeed;
			}else if(!KeyListen.input[8] && KeyListen.input[9]) {
				left = false;
				right = true;
				//Frame.isMoving=true;
				//Frame.dir=Character.moveSpeed;
				//Main.Kylex=Level.kyle.x-8-Frame.sx;
				//Main.Kyley=Level.kyle.y-Frame.sy;
			}else {
				left = false;
				right = false;
			}

			if(KeyListen.input[6] && !KeyListen.input[7]) {
				up=true;
				down=false;
			}else if(!KeyListen.input[6] && KeyListen.input[7]) {
				up=false;
				down=true;
			}else {
				up = false;
				down = false;
			}

			if(KeyListen.inputJustPressed[10] && !KeyListen.inputJustPressed[11]) {
				if(Level.currentEnemy==1){
					Level.currentEnemy=2;
				}else{
					Level.currentEnemy-=1;
				}
			}else if(!KeyListen.inputJustPressed[10] && KeyListen.inputJustPressed[11]) {
				if(Level.currentEnemy==2){
					Level.currentEnemy=1;
				}else{
					Level.currentEnemy+=1;
				}
			}

			if(KeyListen.inputJustPressed[12]) {
				Character.spawningEnemy=true;
				Character.spaceDown=true;
			}

			if(!KeyListen.input[12] && spawningEnemy) {
				Character.spaceDown=false;
			}

			if(KeyListen.inputJustPressed[13]) {
				if(Level.Timer>0){
					Level.Timer=0;
				}else{
					Level.scrollX=0;
				}
			}

		}
	}

	public void tick(){
		//Main.Kylex=x-8-Frame.sx;
		//Main.Kyley=y-Frame.sy;
		//getx=x;
		//gety=y;
		if(Level.Timer>0){
			if(Level.isMoving){
				if(Level.dir==moveSpeed){
					canMove = isCollidingWithWall(new Point(x+width,y), new Point(x+width,y+height-5));
				}else if(Level.dir==-moveSpeed){
					canMove = isCollidingWithWall(new Point(x-2,y+2), new Point(x-2,y+height-5));
				}

				if(!canMove){
					if(Level.dir>0 && x<Level.maxx){
						x+=Level.dir;
					}
					if(Level.dir<0 && x>Level.minx){
						x+=Level.dir;
					}
					if(Level.dir>0 && Level.scrollX<(Level.maxx+16-Frame.gameSize.width) && x>Level.scrollX+(Frame.gameSize.width/2)-moveSpeed && x<Level.scrollX+(Frame.gameSize.width/2)+moveSpeed){
						Level.scrollX+=Level.dir;
					}
					if(Level.dir<0 && Level.scrollX>Level.minx && x<=(startx+Level.scrollX)){
						Level.scrollX+=Level.dir;
					}

					if(!isJumping && !isCollidingWithFloor(new Point(x,(y+height)),new Point((x+16),(y+height)))){
						//if(!fallbypass){
						allowFall=true;
						//}
					}
				}else{
					if(!isJumping && !isCollidingWithFloor(new Point(x,(y+height)),new Point((x+16),(y+height)))){
						x+=0;
						Level.scrollX+=0;
						Level.dir=0;
						//Frame.isMoving=false;
					}/*else{
					x+=0;
					Frame.sx+=0;
					Frame.dir=0;
					//Frame.isMoving=false;
					allowFall=true;
					y+=fallspeed;
				}*/
				}
				if(isJumping){
					if(jumpCount>=jumpHeight){
						if(jumpSpeed==0){
							isJumping=false;
							Level.isJumping=false;
							jumpCount=0;
							allowFall=true;
							jumpSpeed=5;
						}else{
							if(jdecCount>=2){
								jdecCount=0;
								jumpSpeed-=1;
							}else{
								y-=jumpSpeed;
								if(Level.scrollY>0){
									Level.scrollY-=jumpSpeed;
								}
								jdecCount+=1;
							}
						}
					}else{
						if(!isCollidingWithCeiling(new Point(x,y),new Point(x+width,y))){
							y-=jumpSpeed;
							if(Level.scrollY>0){
								Level.scrollY-=jumpSpeed;
							}
							jumpCount+=1;
						}else{
							jumpSpeed=0;
							jumpCount=jumpHeight;
						}
					}
				}
			}

			if(up){
				if(!isCollidingWithCeiling(new Point(x,(y)),new Point((x+16),(y)))){
					y-=verticalMoveSpeed;
				}
			}
			if(down){
				if(!isCollidingWithFloor(new Point(x,(y+height)),new Point((x+16),(y+height)))){
					y+=verticalMoveSpeed;
				}
			}
			if(right) {
				Level.isMoving=true;
				Level.dir=Character.moveSpeed;
				Main.Kylex=Level.kyle.x-8-Level.scrollX;
				Main.Kyley=Level.kyle.y-Level.scrollY;
			}else if(left) {
				Level.isMoving=true;
				Level.dir=-Character.moveSpeed;
				Main.Kylex=Level.kyle.x-8-Level.scrollX;
				Main.Kyley=Level.kyle.y-Level.scrollY;
			}else {
				Level.isMoving=true;
				Level.dir=0;
			}

			if(y-(Frame.gameSize.height/2)<Level.maxy){
				Level.scrollY=y-(Frame.gameSize.height/2);
			}else{
				Level.scrollY=Level.maxy-(Frame.gameSize.height/2);
			}

			if(spawningEnemy && Level.Timer>0 && !spaceDown){
				/*if(spawnSoundPlaying=true){
				playSound();
				//spawnSoundPlaying=false;
			}*/
				spawnEnemy();
			}
		}else{
			y-=3;
		}
	}

	private void playSound(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Spawn.wav")); 
			Clip clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
			clip.open(spawnsound);

			FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(20f * (float) Math.log10(Main.soundVolume));
			//clip.start();

			clip.loop(0);
			if(spawnSoundPlaying=false){
				clip.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void playSoundDeny(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Deny.wav")); 
			Clip clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
			clip.open(spawnsound);

			FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(20f * (float) Math.log10(Main.soundVolume));

			clip.loop(0);
			/*if(spawnSoundPlaying=false){
	        clip.close();
	        }*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void spawnEnemy() {
		if(spawnCount>=spawnTime){
			if(Level.currentEnemy==1){
				if(Level.BlokAllowed && Level.RP>=5){
					Level.RP-=5;
					Level.sprite.add(new Blok(x,y+height+5,16,16,256));
					playSound();
				}else{
					playSoundDeny();
				}
			}
			if(Level.currentEnemy==2){
				if(Level.EliteBlokAllowed && Level.RP>=20){
					Level.RP-=20;
					Level.sprite.add(new Elite_Blok(x,y+height+5,16,16,257));
					playSound();
				}else{
					playSoundDeny();
				}
			}
			spawnCount=0;
			spawnSoundPlaying=true;

			spawningEnemy=false;
		}else{
			spawnCount+=1;
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
								Level.isJumping=true;
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
		//g.drawImage(kylegfx,x-8-Frame.sx,y-Frame.sy,null);
		if(!Level.heroCreated || x+height>0){	
			if(animCount>=animTime){
				animCount=1;
				if(animFrame<4){
					animFrame+=1;
				}else{
					animFrame=1;
				}
			}else{
				animCount+=1;
			}

			if(animFrame==1){
				base.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY);
				feet.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY+34);
				hand1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+24);
				hand2.paintIcon(c,g,x-Level.scrollX+14,y-Level.scrollY+26);
				hoverboard.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+37);
				fire.paintIcon(c,g,x-Level.scrollX+18,y-Level.scrollY+35);
			}
			if(animFrame==2){
				base.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY+1);
				feet.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY+34);
				hand1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+25);
				hand2.paintIcon(c,g,x-Level.scrollX+14,y-Level.scrollY+25);
				hoverboard.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+37);
			}
			if(animFrame==3){
				base.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY);
				feet.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY+34);
				hand1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+26);
				hand2.paintIcon(c,g,x-Level.scrollX+14,y-Level.scrollY+24);
				hoverboard.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+37);
				fire.paintIcon(c,g,x-Level.scrollX+18,y-Level.scrollY+35);
			}
			if(animFrame==4){
				base.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY-1);
				feet.paintIcon(c,g,x-Level.scrollX+3,y-Level.scrollY+34);
				hand1.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+25);
				hand2.paintIcon(c,g,x-Level.scrollX+14,y-Level.scrollY+25);
				hoverboard.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+37);
			}
		}

		if(spawnCount<spawnTime && spawningEnemy && Level.Timer>0){
			if(Level.currentEnemy==1 && Level.BlokAllowed && Level.RP>=5){
				blok.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+height+5);
			}
			if(Level.currentEnemy==2 && Level.EliteBlokAllowed && Level.RP>=25){
				eliteblok.paintIcon(c,g,x-Level.scrollX,y-Level.scrollY+height+5);
			}
		}
	}
	public void readCharAttributes(){
		try{
			String character = new String("assets/PlayerAttributes.txt");
			charfile = new Scanner(new File(character));
			while(charfile.hasNextLine()){
				width=charfile.nextInt();
				height=charfile.nextInt();
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
			System.out.println("error loading player attributes");
		}
	}
}
