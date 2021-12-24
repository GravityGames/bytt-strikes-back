package com.gravitygamesinteractive.byttstrikesback;

import javax.swing.*;
import java.awt.*;

public class Frame{
	private static final long serialVersionUID=1L;
	
	public static Dimension realsize;
	public static Dimension windowSize = new Dimension(640,480);
	public static Dimension gameSize = new Dimension(320,240);
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//public static Dimension gameSize = new Dimension(640,480);
	
	public static String name="Bytt Strikes Back!";
	public static int sx=0,sy=0;
	
	public static int dir;
	public static boolean isMoving=false;
	public static boolean isJumping=false;
	
	public static boolean debug = false;

	public Frame(){
		
}
public static JFrame frame;
public static Main main;
public static void main(String args[]){
	frame=new JFrame();
	
	for(String s:args) {
		
		if(s.equals("-debug") || s.equals("-d")) {
			Frame.debug = true;
		}
		
	}
	
    frame.setSize(windowSize);
    
    frame.addKeyListener(new KeyListen());
    
    main=new Main();
    frame.add(main);
	
	realsize=new Dimension(frame.getWidth(),frame.getHeight());
	
	frame.requestFocus();
	frame.setTitle(name);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	
	
}

}
