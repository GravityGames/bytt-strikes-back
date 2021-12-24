package com.gravitygamesinteractive.byttstrikesback;

import javax.swing.*;
import java.awt.*;

public class Frame{
	private static final long serialVersionUID=1L;
	
	public static Dimension realsize;
	public static Dimension size = new Dimension(640,480);
	
	public static String name="Bytt Strikes Back!";
	public static int sx=0,sy=0;
	
	public static int dir;
	public static boolean isMoving=false;
	public static boolean isJumping=false;

	public Frame(){
		
}
public static JFrame frame;
public static Main main;
public static void main(String args[]){
	JFrame frame=new JFrame();
    frame.setSize(size);
    main=new Main();
    frame.add(main);
	
	realsize=new Dimension(frame.getWidth(),frame.getHeight());
	
	frame.requestFocus();
	frame.setTitle(name);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.addKeyListener(new KeyListen());
	
	
}

}
