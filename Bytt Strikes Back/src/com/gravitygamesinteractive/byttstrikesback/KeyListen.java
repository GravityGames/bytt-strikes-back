package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Dimension;
import java.awt.event.*;

public class KeyListen implements KeyListener{
	public static boolean downpressed=false;

	public static boolean[] input, inputJustPressed;
	public static int[] inputMap;

	public static final int KEYS = 14;

	/*
		Keymapping is as follows:

		0 - Menu Up
		1 - Menu Down
		2 - Menu Left
		3 - Menu Right
		4 - Confirm
		5 - Back

		6 - Move Up
		7 - Move Down
		8 - Move Left
		9 - Move Right
		10 - Cycle Left
		11 - Cycle Right
		12 - Spawn
		13 - End Setup
	 */

	public KeyListen() {
		System.out.println("Test");
		input = new boolean[KEYS];
		inputJustPressed = new boolean[KEYS];
		inputMap = new int[KEYS];

		for(int i=0; i<KEYS; i++) {
			input[i] = false;
			inputJustPressed[i] = false;
		}

		keySetup();

	}

	public void keySetup() {
		inputMap[0] = KeyEvent.VK_UP;
		inputMap[1] = KeyEvent.VK_DOWN;
		inputMap[2] = KeyEvent.VK_LEFT;
		inputMap[3] = KeyEvent.VK_RIGHT;
		inputMap[4] = KeyEvent.VK_Z;
		inputMap[5] = KeyEvent.VK_X;

		inputMap[6] = KeyEvent.VK_UP;
		inputMap[7] = KeyEvent.VK_DOWN;
		inputMap[8] = KeyEvent.VK_LEFT;
		inputMap[9] = KeyEvent.VK_RIGHT;
		inputMap[10] = KeyEvent.VK_Z;
		inputMap[11] = KeyEvent.VK_X;
		inputMap[12] = KeyEvent.VK_SPACE;
		inputMap[13] = KeyEvent.VK_ENTER;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i=0; i<KEYS; i++) {
			if(inputMap[i] == key) {
				if(!input[i]) {
					inputJustPressed[i]=true;
				}
				input[i]=true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i=0; i<KEYS; i++) {
			if(inputMap[i] == key) {
				inputJustPressed[i]=false;
				input[i]=false;
			}
		}
	}

	public void keyTyped(KeyEvent e){
		int key = e.getKeyCode();

		switch(key){
		case KeyEvent.VK_RIGHT:

			break;

		case KeyEvent.VK_LEFT:

			break;
		}
	}
}
