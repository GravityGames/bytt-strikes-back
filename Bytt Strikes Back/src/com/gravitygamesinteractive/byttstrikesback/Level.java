package com.gravitygamesinteractive.byttstrikesback;
 
import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import com.gravitygamesinteractive.byttstrikesback.text.Text;
 
public class Level extends JComponent{
	private static final long serialVersionUID = 1L;
	public static ArrayList<Tile> tile=new ArrayList<Tile>();
	public static ArrayList<Enemy> sprite=new ArrayList<Enemy>();
	public static Character kyle;
	public static Hero hero;
	public static boolean levelOver=false,levelWon=false;
	public static boolean heroCreated=false;
	public static boolean BlokAllowed,EliteBlokAllowed,SpikeBlokAllowed;
	public static int heroHealth=2;
 
	private Scanner s;
	public static int currentEnemy=1;
	public static int RP=100;
	public static int Timer=100;
	public int TimerCount;
	private int objectId,spx,spy,spId,ex2;
	public static int minx,maxx,maxy;
	private String oidstr;
	private String xstr;
	private String ystr;
	private String Idstr;
	private String ex2str;
	public static ArrayList<String> objarray= new ArrayList<String>();
	public static ArrayList<String> xarray= new ArrayList<String>();
	public static ArrayList<String> yarray= new ArrayList<String>();
	public static ArrayList<String> Idarray=new ArrayList<String>();
	public static ArrayList<String> exbit2array=new ArrayList<String>();
	
	public static ImageIcon blok;
	public static ImageIcon eliteblok;
	public Level(){
		stageCfg();
		stageFile();
		setStage();
		setScroll();
		Hero.health=heroHealth;
		Frame.sx=0;
		kyle.x=150;
		kyle.y=240;
		currentEnemy=1;
		heroCreated=false;
		Main.loseFrame=0;
		Main.loseCount=0;
		Main.winFrame=0;
		Main.winCount=0;
		
		try {
    		Tile.tileset1 =  ImageIO.read(new File("res/resources/images/tilesets/tileset1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		blok = new ImageIcon("res/resources/images/EnemyIcons/Blok.png");
		eliteblok = new ImageIcon("res/resources/images/EnemyIcons/EliteBlok.png");
	}
	
	public void input() {
		kyle.input();
	}
 
	public void tick(){
		kyle.tick();
		Main.Kylex=kyle.x-8-Frame.sx;
		Main.Kyley=kyle.y-Frame.sy;
		for(int e=0;e<tile.size();e++){
			if(tile.get(e).x>Frame.sx-32&&tile.get(e).y>Frame.sy-32&&tile.get(e).x<Frame.sx+Frame.gameSize.width+32&&tile.get(e).y<Frame.sy+Frame.gameSize.height+32){
			tile.get(e).tick();
			}
		}
		for(int e=0;e<sprite.size();e++){
			if(sprite.get(e).x>Frame.sx-100&&sprite.get(e).y>Frame.sy-100&&sprite.get(e).x<Frame.sx+Frame.gameSize.width+100&&sprite.get(e).y<Frame.sy+Frame.gameSize.height+100){
			sprite.get(e).tick();
			}
		}
		if(Timer>0){
		if(TimerCount>=62){
			TimerCount=0;
			Timer-=1;
		}else{
			TimerCount+=1;
		}
		}else{
			if(heroCreated){
				hero.tick();
			}else{
				if(Frame.sx<=0){
					Frame.sx=0;
				hero = new Hero(0,300);
				hero.health=heroHealth;
				hero.deathCount=0;
				hero.invincibleCount=0;
				hero.invinciblehurt=false;
				heroCreated=true;
				}else{
					Frame.sx-=3;
				}
			}
		}
	}
 
	public void paintComponent(Graphics2D g){
		g.setColor(new Color(153,217,234));
		g.fillRect(0, 0, Frame.gameSize.width, Frame.gameSize.height);
		//g.setColor(new Color(153,217,234));
		//g.fillRect(0, 0, Frame.size.width, Frame.size.height);
		for(int d=0;d<tile.size();d++){
			if(tile.get(d).x>Frame.sx-32&&tile.get(d).y>Frame.sy-32&&tile.get(d).x<Frame.sx+Frame.gameSize.width+32&&tile.get(d).y<Frame.sy+Frame.gameSize.height+32){
				tile.get(d).render(g,this);
			}
	}
		kyle.render(g,this);
		if(heroCreated){
			hero.render(g,this);
		}
		for(int t=0;t<sprite.size();t++){
			if(sprite.get(t).x>Frame.sx-32&&sprite.get(t).y>Frame.sy-32&&sprite.get(t).x<Frame.sx+Frame.gameSize.width+32&&sprite.get(t).y<Frame.sy+Frame.gameSize.height+32){
			sprite.get(t).render(g,this);
			}	
	}
		if(Timer>0){
		g.setColor(Color.black);
		//g.drawString(Integer.toString(Timer), 600, 10);
		Text.drawString("RECRUIT POINTS:", g, Main.font, 0, 0, Text.LEFT);
		Text.drawString(Integer.toString(RP), g, Main.font, 60, 11, Text.CENTER);
		Text.drawString("TIME:", g, Main.font, Frame.gameSize.width/2, 0, Text.CENTER);
		Text.drawString(Integer.toString(Timer), g, Main.font, Frame.gameSize.width/2, 11, Text.CENTER);
		//g.drawString(Integer.toString(RP) + " Recruit Points", 40, 10);
		Text.drawString("COST:", g, Main.font, Frame.gameSize.width-20, 0, Text.RIGHT);
		if(currentEnemy==1){
			g.setColor(Color.black);
			Text.drawString("5 RP", g, Main.font, Frame.gameSize.width-23, 11, Text.RIGHT);
			//g.drawString(("5RP"), 308, 10);
			//g.drawString(("Blok"), 307, 80);
			blok.paintIcon(this,g,Frame.gameSize.width/2-25,22);
			Text.drawString("BLOK", g, Main.font, Frame.gameSize.width/2, 66, Text.CENTER);
		}if(currentEnemy==2){
			g.setColor(Color.black);
			//g.drawString(("20RP"), 305, 10);
			Text.drawString("20 RP", g, Main.font, Frame.gameSize.width-19, 11, Text.RIGHT);
			//g.drawString(("Elite Blok"), 295, 80);
			eliteblok.paintIcon(this,g,Frame.gameSize.width/2-25,22);
			Text.drawString("ELITE BLOK", g, Main.font, Frame.gameSize.width/2, 66, Text.CENTER);
		}
	}
	}
	
	public void stageCfg(){
		try{
			String stage = Main.levelname;
			s = new Scanner(new File(stage + "cfg.txt"));
			while(s.hasNextLine()){
				Timer=s.nextInt();
				RP=s.nextInt();
				if(s.nextInt()==1){
					BlokAllowed=true;
				}else{
					BlokAllowed=false;
				}
				if(s.nextInt()==1){
					EliteBlokAllowed=true;
				}else{
					EliteBlokAllowed=false;
				}
				if(s.nextInt()==1){
					SpikeBlokAllowed=true;
				}else{
					SpikeBlokAllowed=false;
				}
				heroHealth=s.nextInt();
	}
			s.close();
	}catch(Exception e){
		System.out.println("error loading stage");
	}
 
	}
 
	public void stageFile(){
		try{
			String stage = Main.levelname;
			s = new Scanner(new File(stage + ".txt"));
			while(s.hasNextLine()){
				objectId=s.nextInt();
	            oidstr=Integer.toString(objectId);
				objarray.add(oidstr);
				spx=s.nextInt();
				xstr=Integer.toString(spx);
				xarray.add(xstr);
				spy=s.nextInt();
				ystr=Integer.toString(spy);
				yarray.add(ystr);
				spId=s.nextInt();
				Idstr=Integer.toString(spId);
				Idarray.add(Idstr);
				ex2=s.nextInt();
				ex2str=Integer.toString(ex2);
				exbit2array.add(ex2str);
	}
			s.close();
	}catch(Exception e){
		System.out.println("error loading stage");
	}
 
	}
 
	public void setStage(){
		for(int oa=0; oa<objarray.size(); oa++){
			if(Integer.parseInt(objarray.get(oa))==0){
				kyle=new Character(Integer.parseInt(xarray.get(oa)),Integer.parseInt(yarray.get(oa))+(45-Character.height));
			}else if(Integer.parseInt(objarray.get(oa))>255){
				if(Integer.parseInt(objarray.get(oa))==256){
			//sprite.add(new Aligator(Integer.parseInt(xarray.get(oa)),Integer.parseInt(yarray.get(oa)),35,53,Integer.parseInt(Idarray.get(oa)),256));
			}
			}else{
			tile.add(new Tile(Integer.parseInt(objarray.get(oa)),Integer.parseInt(xarray.get(oa)),Integer.parseInt(yarray.get(oa)),Integer.parseInt(Idarray.get(oa)),Integer.parseInt(exbit2array.get(oa))));
 
			}
		}
	}
	public void setScroll(){
		for(int oa=0; oa<objarray.size(); oa++){
			if(Integer.parseInt(xarray.get(oa))<minx){
				minx=Integer.parseInt(xarray.get(oa));
			}
			if(Integer.parseInt(xarray.get(oa))>maxx){
			maxx=Integer.parseInt(xarray.get(oa));
		}
		if(Integer.parseInt(yarray.get(oa))>maxy){
			maxy=Integer.parseInt(yarray.get(oa));
		}
	}
	}
}