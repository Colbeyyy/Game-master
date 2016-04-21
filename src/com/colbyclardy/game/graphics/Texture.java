package com.colbyclardy.game.graphics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.sprites.Spritesheet;
import com.colbyclardy.game.utils.BufferUtils;
public class Texture {
	private int width, height;
	private int texture;
	
	private int[] pixels;
	
	//private int size;
	private int x,y;
	
	//tiles
	public static Texture grass;
	public static Texture dirt;
	public static Texture grass2;
	
	public static void loadAll()
	{
		grass = new Texture("res/textures/tiles/grass.png");
		dirt = new Texture("res/textures/tiles/dirt.png");
		grass2 = new Texture(Spritesheet.sheet1, 8, 0, 29);
	}
	
	public Texture(String path) {
		texture = loadFromFile(path);
	}
	
	public Texture(Spritesheet sheet, int size, int x, int y)
	{
		this.x = x;
		this.y = y;
		texture = loadFromSpriteSheet(sheet, size, x, y);
	}
	
	private int loadFromSpriteSheet(Spritesheet sheet, int size, int xo, int yo)
	{
		pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(sheet.path));
			width = size;
			height = size;
			pixels = new int[width * height];
			pixels = image.getRGB(xo * size, yo * size, width, height, null, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//sheet.image.getRGB(x * size, y * size, size, size, pixels, 0, size
		
		
		int[] data = new int[pixels.length];
		for (int i = 0; i < data.length; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
			if(data[i] == 0xff000000)
			{
				data[i] = 0;
			}
		}
		
		int result = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, result);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		glBindTexture(GL_TEXTURE_2D, 0);
		return result;
	}
	
	private int loadFromFile(String path) {
		pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//int x = Random.getRandomInt(0, pixels.length);		
		
		int[] data = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		int result = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, result);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		glBindTexture(GL_TEXTURE_2D, 0);
		return result;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
