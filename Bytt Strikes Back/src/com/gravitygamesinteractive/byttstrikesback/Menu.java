package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Menu extends JComponent{
	
	public static int option=1;
	public static int currentMenu=1;
	public static boolean blip=false,confirmed=false;;
	public static ImageIcon pointer;
	public static ImageIcon a,l,p,y;
	public static ImageIcon Stage1,Stage2,Stage3;
	
	public Menu(){
		pointer=new ImageIcon("res/resources/images/Font/Pointer.png");
		a=new ImageIcon("res/resources/images/Font/A.png");
		l=new ImageIcon("res/resources/images/Font/L.png");
		p=new ImageIcon("res/resources/images/Font/P.png");
		y=new ImageIcon("res/resources/images/Font/Y.png");
		Stage1=new ImageIcon("res/resources/images/LevelIcons/1.png");
		Stage2=new ImageIcon("res/resources/images/LevelIcons/2.png");
		Stage3=new ImageIcon("res/resources/images/LevelIcons/3.png");
	}

	public void tick(){
		if(blip){
			playSound();
		}
		if(confirmed){
			playSoundConfirm();
		}
	}
	
	public void paintComponent(Graphics g){
		if(currentMenu==1){
		if (option==1){
			pointer.paintIcon(this,g,Frame.size.width/2-40, 288);
		}
		g.setColor(Color.black);
		g.drawString("Play", Frame.size.width/2-20, 300);
		/*p.paintIcon(this,g,Frame.size.width/2-20,288);
		l.paintIcon(this,g,Frame.size.width/2-4,288);
		a.paintIcon(this,g,Frame.size.width/2+10,288);
		y.paintIcon(this,g,Frame.size.width/2+26,288);*/
		/*g.setColor(Color.GRAY);
		g.drawString("How to Play", Frame.size.width/2-20, 320);
		g.drawString("Options", Frame.size.width/2-20, 340);
		g.drawString("Level Editor", Frame.size.width/2-20, 360);*/
	}else if(currentMenu==2){
		g.setColor(Color.black);
		g.drawString("Choose a Stage!", Frame.size.width/2-40, 20);
		Stage1.paintIcon(this,g,50,100);
		Stage2.paintIcon(this,g,150,100);
		Stage3.paintIcon(this,g,250,100);
		if (option==1){
			pointer.paintIcon(this,g,30, 115);
		}
		if (option==2){
			pointer.paintIcon(this,g,130, 115);
		}
		if (option==3){
			pointer.paintIcon(this,g,230, 115);
		}
	}
	}
	private void playSound(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("MenuBeep.wav")); 
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
	        clip.open(spawnsound);
	        clip.loop(0);
	        /*if(jumpedsound=true){
	        clip.close();
	        }*/
	        blip=false;
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	private void playSoundConfirm(){
		try{
			BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Confirm.wav")); 
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
	        clip.open(spawnsound);
	        clip.loop(0);
	        /*if(jumpedsound=true){
	        clip.close();
	        }*/
	        confirmed=false;
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
}
