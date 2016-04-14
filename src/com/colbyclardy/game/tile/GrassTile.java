package com.colbyclardy.game.tile;

import com.colbyclardy.game.graphics.Texture;

public class GrassTile extends Tile{

	public GrassTile()
	{
		texture = Texture.dirt;
	}
	
	public void update()
	{
		rotation.y += 0.05f;
		rotation.z += 0.005f;
		position.z = -5.5f;
	}
	
}
