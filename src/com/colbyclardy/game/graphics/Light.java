package com.colbyclardy.game.graphics;

import com.colbyclardy.game.math.Vector3f;

public class Light {

	public Vector3f position;
	public Vector3f color;
	
	public Light()
	{
		position = new Vector3f();
		color = new Vector3f();
	}
	
	public Light(Vector3f position, Vector3f color)
	{
		this.position = position;
		this.color = color;
	}
	
}
