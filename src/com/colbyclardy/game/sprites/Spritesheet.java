package com.colbyclardy.game.sprites;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public int width;

	public int height;
	
	public int[] pixels;
	
	public int size;
	
	public static Spritesheet sheet1 = new Spritesheet("res/textures/spritesheet/spritesheet1.png", 8);
	
	public Spritesheet(String path, int size)
	{
		load(path);
		this.size = size;
	}
	
	private void load(String path)
	{
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(pixels[0] + " " + pixels[257]);
	}
}
