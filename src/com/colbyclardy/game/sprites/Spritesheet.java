package com.colbyclardy.game.sprites;

public class Spritesheet {

	public int width;

	public int height;
	
	public int[] pixels;
	
	public int size;
	
	public String path;
	
	public static Spritesheet sheet1 = new Spritesheet("res/textures/spritesheet/spritesheet1.png", 8);
	public static Spritesheet fonts = new Spritesheet("res/textures/spritesheet/fontsheet.png", 16);
	
	public Spritesheet(String path, int size)
	{
		this.path = path;
		this.size = size;
	}
}
