package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import com.gravitygamesinteractive.byttstrikesback.text.Text;

public class Menu extends JComponent{

	public static int option=1;
	public static int currentMenu=1;
	public static int screenSize = 2;
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

	public void input() {
		if(KeyListen.inputJustPressed[0] && !KeyListen.inputJustPressed[1]) {
			if(Menu.currentMenu==1){
				if(Menu.option==1){
					Menu.option=5;
				}else{
					Menu.option--;
				}
				//Menu.option-=1;
				Menu.blip=true;
			}else if(Menu.currentMenu==3){
				if(Menu.option==1){
					Menu.option=4;
				}else{
					Menu.option--;
				}
				//Menu.option-=1;
				Menu.blip=true;
			}else if(Menu.currentMenu==7){
				if(Menu.option==1){
					Menu.option=2;
				}else{
					Menu.option--;
				}
				//Menu.option-=1;
				Menu.blip=true;
			}
		}else if(!KeyListen.inputJustPressed[0] && KeyListen.inputJustPressed[1]) {
			if(Menu.currentMenu==1){
				if(Menu.option==5){
					Menu.option=1;
				}else{
					Menu.option++;
				}
				//Menu.option+=1;
				Menu.blip=true;
			}else if(Menu.currentMenu==3){
				if(Menu.option==4){
					Menu.option=1;
				}else{
					Menu.option++;
				}
				//Menu.option+=1;
				Menu.blip=true;
			}else if(Menu.currentMenu==7){
				if(Menu.option==2){
					Menu.option=1;
				}else{
					Menu.option++;
				}
				//Menu.option+=1;
				Menu.blip=true;
			}
		}

		if(KeyListen.inputJustPressed[2] && !KeyListen.inputJustPressed[3]) {
			if(Menu.currentMenu==2){
				if(Menu.option==1){
					Menu.option=3;
				}else{
					Menu.option-=1;
				}
				Menu.blip=true;
			}else if(Menu.currentMenu==6){
				if(Menu.option==1){
					if(Menu.screenSize>1){
						Menu.screenSize--;
						Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
						Frame.frame.setSize(Frame.windowSize);
						Frame.frame.setLocationRelativeTo(null);
					}else{
						Menu.screenSize=Frame.screenSize.height/Frame.gameSize.height;
						Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
						Frame.frame.setSize(Frame.windowSize);
						Frame.frame.setLocationRelativeTo(null);
					}
				}else{

				}
				Menu.blip=true;
			}else if(Menu.currentMenu==7){
				if(Menu.option==1){
					if(Main.musicVolume <= 0.01f) {
						Main.musicVolume = 0.0f;
					}else {
						Main.musicVolume -= 0.01f;
					}
				}else if(Menu.option==2){
					if(Main.soundVolume <= 0.01f) {
						Main.soundVolume = 0.0f;
					}else {
						Main.soundVolume -= 0.01f;
					}
				}
			}
		}else if(!KeyListen.inputJustPressed[2] && KeyListen.inputJustPressed[3]) {
			if(Menu.currentMenu==2){
				if(Menu.option==3){
					Menu.option=1;
				}else{
					Menu.option+=1;
				}
				Menu.blip=true;
			}else if(Menu.currentMenu==6){
				if(Menu.option==1){
					if(Menu.screenSize<Frame.screenSize.height/Frame.gameSize.height){
						Menu.screenSize++;
						Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
						Frame.frame.setSize(Frame.windowSize);
						Frame.frame.setLocationRelativeTo(null);
					}else{
						Menu.screenSize=1;
						Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
						Frame.frame.setSize(Frame.windowSize);
						Frame.frame.setLocationRelativeTo(null);
					}
				}else{

				}
				Menu.blip=true;
			}else if(Menu.currentMenu==7){
				if(Menu.option==1){
					if(Main.musicVolume >= 0.99f) {
						Main.musicVolume = 1.0f;
					}else {
						Main.musicVolume += 0.01f;
					}
				}else if(Menu.option==2){
					if(Main.soundVolume >= 0.99f) {
						Main.soundVolume = 1.0f;
					}else {
						Main.soundVolume += 0.01f;
					}
				}
			}
		}

		if(KeyListen.inputJustPressed[4] && !KeyListen.inputJustPressed[5]) {
			Menu.confirmed=true;
			if(Menu.currentMenu==1){
				if(Menu.option==1){
					Menu.currentMenu=2;
					Menu.option=1;
				}else if(Menu.option==2){
					Menu.currentMenu=3;
					Menu.option=1;
				}else if(Menu.option==3){
					Menu.currentMenu=4;
					Menu.option=1;
				}
			}else if(Menu.currentMenu==2){
				if(Menu.option==1){
					Main.levelname=new String("assets/VerdantValley1");
				}
				if(Menu.option==2){
					Main.levelname=new String("assets/VerdantValley2");
				}
				if(Menu.option==3){
					Main.levelname=new String("assets/VerdantValley4");
				}
				Main.menuon=false;
				Level.levelOver=false;
				Level.levelWon=false;
			}else if(Menu.currentMenu==3){
				if(Menu.option==1){
					//Menu.currentMenu=2;
				}else if(Menu.option==2){
					Menu.currentMenu=6;
				}else if(Menu.option==3){
					Menu.currentMenu=7;
				}
				Menu.option=1;
			}else if(Menu.currentMenu==4){
				Menu.currentMenu=1;
				Menu.option = 1;
			}
		}else if(!KeyListen.inputJustPressed[4] && KeyListen.inputJustPressed[5]) {
			Menu.confirmed=true;
			if(Menu.currentMenu==1){

			}else if(Menu.currentMenu==2){
				Menu.currentMenu=1;
				Menu.option=1;
			}else if(Menu.currentMenu==3){
				Menu.currentMenu=1;
				Menu.option=1;
			}else if(Menu.currentMenu==6 || Menu.currentMenu==7){
				Menu.currentMenu=3;
				Menu.option=1;
			}
		}

	}

	public void tick(){
		if(blip){
			playSound();
		}
		if(confirmed){
			playSoundConfirm();
		}
	}

	public void paintComponent(Graphics2D g){
		if(currentMenu==1){
			if (option==1){
				pointer.paintIcon(this,g,Frame.gameSize.width/2-60, 150);
			}else if (option==2){
				pointer.paintIcon(this,g,Frame.gameSize.width/2-60, 165);
			}else if (option==3){
				pointer.paintIcon(this,g,Frame.gameSize.width/2-60, 180);
			}else if (option==4){
				pointer.paintIcon(this,g,Frame.gameSize.width/2-60, 195);
			}else if (option==5){
				pointer.paintIcon(this,g,Frame.gameSize.width/2-60, 210);
			}

			//Text text = new Text("PQRSTUVWXYZ[\\]^_");
			//text.render(g, Main.font, Frame.gameSize.width/2, 150);
			g.setColor(Color.black);
			//g.drawString("Start", Frame.gameSize.width/2-20, 170);
			Text.drawString("START", g, Main.font, Frame.gameSize.width/2, 155, Text.CENTER);
			Text.drawString("OPTIONS", g, Main.font, Frame.gameSize.width/2, 170, Text.CENTER);
			//g.drawString("Options", Frame.gameSize.width/2-20, 185);
			Text.drawString("CREDITS", g, Main.font, Frame.gameSize.width/2, 185, Text.CENTER);
			//g.drawString("Mods", Frame.gameSize.width/2-20, 200);
			Text.drawString("MODS", g, Main.font, Frame.gameSize.width/2, 200, Text.CENTER);
			//g.drawString("Quit", Frame.gameSize.width/2-20, 215);
			Text.drawString("QUIT", g, Main.font, Frame.gameSize.width/2, 215, Text.CENTER);	
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
			//g.drawString("Choose a Stage!", Frame.gameSize.width/2-40, 20);
			Text.drawString("CHOOSE A STAGE!", g, Main.font, Frame.gameSize.width/2, 0, Text.CENTER);
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
		}else if(currentMenu==3){
			Text.drawString("CONFIGURE CONTROLS", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("VIDEO OPTIONS", g, Main.font, Frame.gameSize.width/2, 45, Text.CENTER);
			Text.drawString("AUDIO OPTIONS", g, Main.font, Frame.gameSize.width/2, 60, Text.CENTER);
			Text.drawString("GAME OPTIONS", g, Main.font, Frame.gameSize.width/2, 75, Text.CENTER);
			if (option==1){
				pointer.paintIcon(this,g,60, 30-5);
			}
			if (option==2){
				pointer.paintIcon(this,g,60, 45-5);
			}
			if (option==3){
				pointer.paintIcon(this,g,60, 60-5);
			}
			if (option==4){
				pointer.paintIcon(this,g,60, 75-5);
			}
			//Text.drawString("CONFIGURE CONTROLS", g, Main.font, Frame.gameSize.width/2, 90, Text.CENTER);
		}else if(currentMenu==4){
			Text.drawString("FONT BY CLINT BELLANGER", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);

			Text.drawString("EVERYTHING ELSE BY", g, Main.font, Frame.gameSize.width/2, 60, Text.CENTER);
			Text.drawString("IMPOSSIBLE REALMS/", g, Main.font, Frame.gameSize.width/2, 75, Text.CENTER);
			Text.drawString("GRAVITY GAMES INTERACTIVE", g, Main.font, Frame.gameSize.width/2, 90, Text.CENTER);
		}else if(currentMenu==5){

		}else if(currentMenu==6){
			//Text.drawString("SCREEN SIZE", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("SCREEN SIZE   < " + Integer.toString((Frame.windowSize.width/Frame.gameSize.width)) + "X >", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
		}else if(currentMenu==7){
			//Text.drawString("SCREEN SIZE", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("MUSIC VOLUME   < " + Integer.toString((int)(Main.musicVolume * 100)) + "% >", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("SFX VOLUME   < " + Integer.toString((int)(Main.soundVolume * 100)) + "% >", g, Main.font, Frame.gameSize.width/2, 45, Text.CENTER);

			if (option==1){
				pointer.paintIcon(this,g,45, 30-5);
			}
			if (option==2){
				pointer.paintIcon(this,g,45, 45-5);
			}
			//}

			//Text.drawString("CONFIGURE CONTROLS", g, Main.font, Frame.gameSize.width/2, 90, Text.CENTER);
		}else if(currentMenu==4){
			Text.drawString("FONT BY CLINT BELLANGER", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);

			Text.drawString("EVERYTHING ELSE BY", g, Main.font, Frame.gameSize.width/2, 60, Text.CENTER);
			Text.drawString("IMPOSSIBLE REALMS/", g, Main.font, Frame.gameSize.width/2, 75, Text.CENTER);
			Text.drawString("GRAVITY GAMES INTERACTIVE", g, Main.font, Frame.gameSize.width/2, 90, Text.CENTER);
		}else if(currentMenu==5){

		}else if(currentMenu==6){
			//Text.drawString("SCREEN SIZE", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("SCREEN SIZE   < " + Integer.toString((Frame.windowSize.width/Frame.gameSize.width)) + "X >", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
		}else if(currentMenu==7){
			//Text.drawString("SCREEN SIZE", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("MUSIC VOLUME   < " + Integer.toString((int)(Main.musicVolume * 100)) + "% >", g, Main.font, Frame.gameSize.width/2, 30, Text.CENTER);
			Text.drawString("SFX VOLUME   < " + Integer.toString((int)(Main.soundVolume * 100)) + "% >", g, Main.font, Frame.gameSize.width/2, 45, Text.CENTER);

			if (option==1){
				pointer.paintIcon(this,g,45, 30-5);
			}
			if (option==2){
				pointer.paintIcon(this,g,45, 45-5);
			}
		}

	}
	private void playSound(){
		if(Main.soundVolume > 0 && Main.soundVolume <= 1) {
			try{
				BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("MenuBeep.wav")); 
				Clip clip = AudioSystem.getClip();
				// getAudioInputStream() also accepts a File or InputStream
				AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
				clip.open(spawnsound);
				//clip.loop(0);

				FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				control.setValue(20f * (float) Math.log10(Main.soundVolume));
				clip.start();
				/*if(jumpedsound=true){
		        clip.close();
		        }*/
				blip=false;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private void playSoundConfirm(){
		if(Main.soundVolume > 0 && Main.soundVolume <= 1) {
			try{
				BufferedInputStream spawn = new BufferedInputStream(getClass().getResourceAsStream("Confirm.wav")); 
				Clip clip = AudioSystem.getClip();
				// getAudioInputStream() also accepts a File or InputStream
				AudioInputStream spawnsound = AudioSystem.getAudioInputStream(spawn);
				clip.open(spawnsound);

				FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				control.setValue(20f * (float) Math.log10(Main.soundVolume));
				clip.start();
				/*if(jumpedsound=true){
		        clip.close();
		        }*/
				confirmed=false;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
