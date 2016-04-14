package com.colbyclardy.game.entity;

import com.colbyclardy.game.graphics.Texture;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.sprites.Sprite;

public class TestMob extends Mob{
	
	private Sprite cat = new Sprite(5, new Texture("res/textures/entities/nyancat.png"));
	private Sprite rainbow = new Sprite(10, new Texture("res/textures/entities/nyancatrainbow.png"));
	private Sprite rainbow2 = new Sprite(10, new Texture("res/textures/entities/nyancatrainbow2.png"));
	
	private float x = 0;
	
	public TestMob()
	{
		position.z = 1;
	}
	
	public TestMob(Vector3f position)
	{
		this.position = position;
	}
	
	public void update()
	{
		//head.position = position;
		cat.position = position;
		rainbow.position.y = position.y;
		rainbow.position.x = position.x - 5f;
		rainbow2.position = rainbow.position;
		
		//position.x += 0.05f;
		
		x += 0.05f;
		position.y = (float)Math.sin((double)x);
	}
	
	public void render()
	{
		rainbow.render();
		//cat.render();
	}
	
	
}
