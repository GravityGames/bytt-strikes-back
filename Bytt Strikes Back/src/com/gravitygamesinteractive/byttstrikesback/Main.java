package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.gravitygamesinteractive.byttstrikesback.text.Font;
import com.gravitygamesinteractive.byttstrikesback.text.Text;

import resources.ResourceLoader;

public class Main extends JPanel implements Runnable{
	private static final long serialVersionUID=1L;
	public static boolean isRunning=false;
	public static boolean menuon=true;
	public static String levelname;
	public static boolean stageCreated=false,statscreenover=false;
	public static int winCount,winTime=5,winFrame=1,loseCount,loseTime=5,loseFrame=1;

	public static float musicVolume = 1.0f, soundVolume = 1.0f;

	public static ImageIcon basewin,baselose;
	public static ImageIcon hand1;
	public static ImageIcon hand2;
	public static ImageIcon feet;

	public static BufferedImage screen;
	Graphics2D g2;

	public static Level level;
	public static Menu menu;

	public static int Kylex, Kyley;

	public static Font font;
	public ArrayList<Text> text;

	public Main(){

		screen = new BufferedImage(Frame.gameSize.width, Frame.gameSize.height, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)screen.getGraphics();

		font = new Font();
		text = new ArrayList<Text>();
		text.add(new Text("!"));

		basewin = new ImageIcon("res/resources/images/Bytt/BaseSmile.png");
		baselose = new ImageIcon("res/resources/images/Bytt/BaseDead.png");
		hand1 = new ImageIcon("res/resources/images/Bytt/Hand1.png");
		hand2 = new ImageIcon("res/resources/images/Bytt/Hand2.png");
		feet = new ImageIcon("res/resources/images/Bytt/Feet.png");
		start();
	}
	public void start(){
		menu=new Menu();
		//level=new Level();

		//Kylex=level.kyle.x-8;
		//Kyley=level.kyle.y;


		isRunning=true;
		new Thread(this).start();
	}

	public void input() {
		if(menuon){
			menu.input();
		}else {
			if(stageCreated){
				level.input();
			}
		}
		
		if(Level.levelOver){
			for(int i=0; i<KeyListen.inputJustPressed.length; i++) {
				if(KeyListen.inputJustPressed[i]) {
					Main.statscreenover=true;
					Level.levelOver=false;
					Level.levelWon=false;
				}
			}
		}

		for(int i=0; i<KeyListen.inputJustPressed.length; i++) {
			KeyListen.inputJustPressed[i] = false;
		}
	}

	public void tick(){
		//Kylex=level.kyle.x-8-Frame.sx;
		//Kyley=level.kyle.y-Frame.sy;
		if(menuon){
			menu.tick();
			statscreenover=false;

		}else{
			if(stageCreated){
				level.tick();
				if(level.levelOver && level.levelWon && statscreenover){
					level.tile.clear();
					level.sprite.clear();
					level.objarray.clear();
					level.xarray.clear();
					level.yarray.clear();
					level.Idarray.clear();
					level.exbit2array.clear();
					menuon=true;
					stageCreated=false;
				}else if(level.levelOver && statscreenover && !level.levelWon){
					level.tile.clear();
					level.sprite.clear();
					level.objarray.clear();
					level.xarray.clear();
					level.yarray.clear();
					level.Idarray.clear();
					level.exbit2array.clear();
					level.levelOver=false;
					statscreenover=false;
					stageCreated=false;
				}
			}else{
				level = new Level();
				stageCreated=true;
			}

			//kyle
			/*if(!level.kyle.isJumping && level.kyle.allowFall && !level.kyle.isCollidingWithFloor(new Point(level.kyle.x,(level.kyle.y+level.kyle.height-1)),new Point((level.kyle.x+16),(level.kyle.y+level.kyle.height-1)))){
			level.kyle.allowFall=true;
			if(level.kyle.fallspeed<5){
			if(level.kyle.jdecCount>=2){
				level.kyle.jdecCount=0;
				level.kyle.fallspeed+=1;
			}else{
				level.kyle.y+=level.kyle.fallspeed;
				if(Frame.sy>0){
					Frame.sy+=level.kyle.fallspeed;
				}
				level.kyle.jdecCount+=1;
			}
			}else{
				level.kyle.y+=level.kyle.fallspeed;

		    }
		if(Frame.sy<(Level.maxy+16-Frame.size.height)){
			Frame.sy+=(level.kyle.fallspeed);
		}
		}else{
			level.kyle.fallspeed=1;
				if(Frame.isJumping){	
					level.kyle.isJumping=true;
					//System.out.println(true);
					level.kyle.allowFall=true;
				}else{
					level.kyle.y=level.kyle.tiley-level.kyle.height;
				level.kyle.allowFall=false;
				level.kyle.jumpCount=0;
				}
			}

		if(Frame.isMoving){
			if(Frame.dir==level.kyle.moveSpeed){
				level.kyle.canMove = level.kyle.isCollidingWithWall(new Point(level.kyle.x+level.kyle.width,level.kyle.y), new Point(level.kyle.x+level.kyle.width,level.kyle.y+level.kyle.height-5));
			}else if(Frame.dir==-level.kyle.moveSpeed){
				level.kyle.canMove = level.kyle.isCollidingWithWall(new Point(level.kyle.x-2,level.kyle.y+2), new Point(level.kyle.x-2,level.kyle.y+level.kyle.height-5));
			}

			if(!level.kyle.canMove){
			if(Frame.dir>0 && level.kyle.x<Level.maxx){
				level.kyle.x+=Frame.dir;
			}
			if(Frame.dir<0 && level.kyle.x>Level.minx){
				level.kyle.x+=Frame.dir;
			}
			if(Frame.dir>0 && Frame.sx<(Level.maxx+16-Frame.size.width) && level.kyle.x>=(level.kyle.startx+Frame.sx)){
			Frame.sx+=Frame.dir;
			}
			if(Frame.dir<0 && Frame.sx>Level.minx && level.kyle.x<=(level.kyle.startx+Frame.sx)){
				Frame.sx+=Frame.dir;
			}
			if(!level.kyle.isJumping && !level.kyle.isCollidingWithFloor(new Point(level.kyle.x,(level.kyle.y+level.kyle.height)),new Point((level.kyle.x+16),(level.kyle.y+level.kyle.height)))){
				//if(!fallbypass){
				level.kyle.allowFall=true;
				//}
				}
			}else{
				level.kyle.x+=0;
				Frame.sx+=0;
				Frame.dir=0;
				Frame.isMoving=false;
			}
			if(level.kyle.isJumping){
				if(level.kyle.jumpCount>=level.kyle.jumpHeight){
					if(level.kyle.jumpSpeed==0){
						level.kyle.isJumping=false;
					Frame.isJumping=false;
					level.kyle.jumpCount=0;
					level.kyle.allowFall=true;
					level.kyle.jumpSpeed=5;
					}else{
						if(level.kyle.jdecCount>=2){
							level.kyle.jdecCount=0;
							level.kyle.jumpSpeed-=1;
						}else{
							level.kyle.y-=level.kyle.jumpSpeed;
							if(Frame.sy>0){
								Frame.sy-=level.kyle.jumpSpeed;
							}
							level.kyle.jdecCount+=1;
						}
					}
				}else{
					level.kyle.y-=level.kyle.jumpSpeed;
					if(Frame.sy>0){
						Frame.sy-=level.kyle.jumpSpeed;
					}
					level.kyle.jumpCount+=1;
				}
			}
		}
		if(level.kyle.invinciblehurt){
			if(level.kyle.invincibleCount>=level.kyle.invincibleTime){
				level.kyle.invincibleCount=0;
				level.kyle.invinciblehurt=false;
			}else{
				level.kyle.invincibleCount+=1;
			}
		}
		if(level.kyle.invinciblepower){
			if(level.kyle.invincibleCount>=level.kyle.invincibleTime){
				level.kyle.invincibleCount=0;
				level.kyle.invinciblehurt=false;
			}else{
				level.kyle.invincibleCount+=1;
			}
		}
		if(level.kyle.health<=0){
			if(level.kyle.deathCount>=level.kyle.deathTime){
				level.kyle.allowFall=true;
				level.kyle.y+=3;
			}else{
				level.kyle.deathCount+=1;
			}
		}
		//endkyle
		Kylex=((level.kyle.x-8)-Frame.sx);
		Kyley=((level.kyle.y)-Frame.sy);*/

		/*	for(int e=0;e<level.tile.size();e++){
				if(level.tile.get(e).x>Frame.sx-32&&level.tile.get(e).y>Frame.sy-32&&level.tile.get(e).x<Frame.sx+Frame.gameSize.width+32&&level.tile.get(e).y<Frame.sy+Frame.gameSize.height+32){
					level.tile.get(e).tick();
				}
			}
			for(int e=0;e<level.sprite.size();e++){
				if(level.sprite.get(e).x>Frame.sx-100&&level.sprite.get(e).y>Frame.sy-100&&level.sprite.get(e).x<Frame.sx+Frame.gameSize.width+100&&level.sprite.get(e).y<Frame.sy+Frame.gameSize.height+100){
					level.sprite.get(e).tick();
				}
			}*/
		}
		//repaint();
		//System.out.println(Kylex);
		//System.out.println(Kyley);
	}

	public void paintComponent(Graphics g){

		//super.paint(g);

		g2.setColor(new Color(153,217,234));
		g2.fillRect(0, 0, Frame.gameSize.width, Frame.gameSize.height);
		if(menuon){
			menu.paintComponent(g2);
		}
		if(stageCreated && !menuon){
			if(!level.levelOver){
				level.paintComponent(g2);
			}else{
				if(level.levelWon){
					if(winCount>=winTime){
						winCount=1;
						if(winFrame<4){
							winFrame+=1;
						}else{
							winFrame=1;
						}
					}else{
						winCount+=1;
					}
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(0, 0, Frame.gameSize.width, Frame.gameSize.height);
					g2.setColor(Color.WHITE);
					//g2.drawString("Bytt Wins! Press a game key to continue.", Frame.gameSize.width/2-120, 10);
					Text.drawString("BYTT WINS!", g2, Main.font, Frame.gameSize.width/2, 0, Text.CENTER);
					Text.drawString("PRESS A GAME KEY TO CONTINUE.", g2, Main.font, Frame.gameSize.width/2, 11, Text.CENTER);
					if(winFrame==1){
						basewin.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+1);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+24);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+27);
					}
					if(winFrame==2 || winFrame==4){
						basewin.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+25);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+25);
					}
					if(winFrame==3){
						basewin.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2-1);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+23);
					}
				}else{
					if(loseCount>=loseTime){
						loseCount=1;
						if(loseFrame<9){
							loseFrame+=1;
						}else{

						}
					}else{
						loseCount+=1;
					}
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(0, 0, Frame.gameSize.width, Frame.gameSize.height);
					g2.setColor(Color.WHITE);
					//g2.drawString("Sproing Wins! Press a game key to retry.", Frame.gameSize.width/2-120, 10);
					Text.drawString("SPROING WINS!", g2, Main.font, Frame.gameSize.width/2, 0, Text.CENTER);
					Text.drawString("PRESS A GAME KEY TO RETRY.", g2, Main.font, Frame.gameSize.width/2, 11, Text.CENTER);
					if(loseFrame==1){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+24);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+26);
					}
					if(loseFrame==2){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+1);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+25);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+27);
					}
					if(loseFrame==3){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+2);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+28);
					}
					if(loseFrame==4){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+29);
					}
					if(loseFrame==5){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+29);
					}
					if(loseFrame==6){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+30);
					}
					if(loseFrame==7){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+31);
					}
					if(loseFrame==8){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+32);
					}
					if(loseFrame==9){
						baselose.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+3);
						feet.paintIcon(this,g2,Frame.gameSize.width/2+3,Frame.gameSize.height/2+34);
						hand1.paintIcon(this,g2,Frame.gameSize.width/2,Frame.gameSize.height/2+26);
						hand2.paintIcon(this,g2,Frame.gameSize.width/2+14,Frame.gameSize.height/2+33);
					}
				}
			}
		}


		g.drawImage(screen, 0, 0, Frame.windowSize.width, Frame.windowSize.height, 0, 0, Frame.gameSize.width, Frame.gameSize.height, null);

	}

	public void run(){
		//screen=createVolatileImage(Frame.size.width,Frame.size.height);
		while(isRunning){
			input();
			tick();
			repaint();

			try{
				Thread.sleep(16);
			}catch(Exception e){

			}
		}
	}

}