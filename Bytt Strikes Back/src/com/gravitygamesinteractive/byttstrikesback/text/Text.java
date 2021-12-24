package com.gravitygamesinteractive.byttstrikesback.text;

import java.awt.Color;
import java.awt.Graphics2D;

public class Text {
	
	public String text;
	
	public static int LEFT = 0;
	public static int CENTER = 1;
	public static int RIGHT = 2;
	
	public Text(String text){
		this.text = text;
	}
	
	public void render(Graphics2D g, Font font, int x, int y){
		for(int i=0; i<text.length(); i++){
			font.glyphs[font.getGlyph(text.charAt(i))].render(g, x, y);;
			x+=font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
		}
	}
	
	public static void drawString(String text, Graphics2D g, Font font, int x, int y){
		drawString(text, g, font, x, y, LEFT);
	}
	
	public static void drawString(String text, Graphics2D g, Font font, int x, int y, int alignment){
		if(alignment == LEFT){
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == 0x20 || text.charAt(i)<0){
					x+=8;
				}else{
					font.glyphs[font.getGlyph(text.charAt(i))].render(g, x, y);;
					x+=font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
				}	
			}
		}else if(alignment == CENTER){
			int textWidth = 0;
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == 0x20 || text.charAt(i)<0){
					textWidth +=8;
				}else{
					textWidth += font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
				}
			}
			x-=textWidth/2;
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == 0x20 || text.charAt(i)<0){
					x+=8;
				}else{
					font.glyphs[font.getGlyph(text.charAt(i))].render(g, x, y);;
					x+=font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
				}
			}
		}else if(alignment == RIGHT){
			int textWidth = 0;
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == 0x20 || text.charAt(i)<0){
					textWidth +=8;
				}else{
					textWidth += font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
				}
			}
			x-=textWidth;
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == 0x20 || text.charAt(i)<0){
					x+=8;
				}else{
					font.glyphs[font.getGlyph(text.charAt(i))].render(g, x, y);;
					x+=font.glyphs[font.getGlyph(text.charAt(i))].width + 1;
				}	
			}
		}
	}

}
