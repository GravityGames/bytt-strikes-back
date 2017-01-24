package com.gravitygamesinteractive.byttstrikesback.text;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Font {
	
	public Image[] fonts;
	public boolean isUnicode = true;
	public Glyph[] glyphs;
	
	public Font(){
		loadFont("res/resources/images/Font/fontdef.s2f");
	}
	
	public int getGlyph(char glyph){
		if(isUnicode){
			return glyph - 0x21;
		}
		return glyph;
	}
	
	public void loadFont(String fileName){
		
		System.out.println("Loading Font File...");
		FileInputStream in = null;
		
		try {
            in = new FileInputStream(fileName);
            
            String str = "";
            
            for(int i=0; i<3; i++){
            	str = str + (char)in.read();
            }
            
            System.out.println("STR: " + str);
            
            if(!str.equals("s2f")){
            	in.close();
            	return;
            }
            
            int numberOfFiles = in.read();
            numberOfFiles++;
            
            System.out.println("Number of Files: " + numberOfFiles);
            
            for(int i=0; i<numberOfFiles; i++){
            	int numberOfCharsInPath = 0;
            	for(int j=0; j<4; j++){
            		numberOfCharsInPath = numberOfCharsInPath << 8;
            		numberOfCharsInPath += in.read();
            	}
            	numberOfCharsInPath++;
            	System.out.println("Number of Characters in Path: " + numberOfCharsInPath);
            	String fontPath = "";
            	fonts = new Image[numberOfFiles];
            	for(int j=0; j<numberOfCharsInPath; j++){
            		fontPath = fontPath + (char)in.read();
            	}
            	System.out.println("Font Image File Path: " + fontPath);
            	try {
        			fonts[i] = ImageIO.read(new File(fontPath));
        			System.out.println("Loaded Font Image!");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            }
            
            int numberOfGlyphs = 0;
            
            for(int i=0; i<4; i++){
            	numberOfGlyphs = numberOfGlyphs << 8;
            	numberOfGlyphs += in.read();
        	}
            
            glyphs = new Glyph[numberOfGlyphs];
            
            for(int i=0; i<numberOfGlyphs; i++){
            	System.out.println("Now Loading Glyph " + (i+33));
            	int x = in.read();
            	x = x << 8;
            	x += in.read();
            	System.out.println("X Pos: " + x);
            	int y = in.read();
            	y = y << 8;
            	y += in.read();
            	System.out.println("Y Pos: " + y);
            	int width = in.read();
            	width = width << 8;
            	width += in.read();
            	System.out.println("Width: " + width);
            	int height = in.read();
            	height = height << 8;
            	height += in.read();
            	System.out.println("Height: " + height);
            	int xOffset = in.read();
            	System.out.println("X Offset: " + xOffset);
            	int yOffset = in.read();
            	System.out.println("Y Offset: " + yOffset);
            	int fileID = in.read();
            	
        		glyphs[i] = new Glyph(x, y, width, height, xOffset, yOffset, fileID, this);
            }
            
            //int c;

            //while ((c = in.read()) != -1) {
            //    out.write(c);
            //}
            in.close();
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
