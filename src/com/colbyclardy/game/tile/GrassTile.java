package com.colbyclardy.game.tile;

import com.colbyclardy.game.graphics.Texture;

public class GrassTile extends Tile{

	public GrassTile()
	{
		texture = Texture.grass;
	}
	
	public void update()
	{
		
		rotation.y += 0.5f;
		//rotation.y += 0.5f;
		position.z = -5.5f;
	}
	
}
