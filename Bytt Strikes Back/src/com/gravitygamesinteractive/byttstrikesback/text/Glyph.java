// The glyph class for the text rendering system. This stores the dimensions for each character
// and is also called upon by the text class to draw the respective glyph.

package com.gravitygamesinteractive.byttstrikesback.text;

import java.awt.Graphics2D;

public class Glyph {
	
	// These values correspond to the position of the glyph on the texture, and NOT the position
	// on the screen.
	public int x;
	public int y;
	public int xOffset;
	public int yOffset;
	public int width;
	public int height;
	public int fontID;
	
	public Font font;
	
	public Glyph(int x, int y, int width, int height, int xOffset, int yOffset, int fontID, Font font){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.font = font;
		this.fontID = fontID;
	}
	
	public void render(Graphics2D g, int xPos, int yPos){
		//g.drawImage(font.font, xPos, yPos, null);
		g.drawImage(font.fonts[fontID], xPos+xOffset, yPos+yOffset, xPos+width+xOffset, yPos+height+yOffset, x, y, x+width, y+height, null);
	}

}
