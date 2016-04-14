package com.colbyclardy.game.entity;

import com.colbyclardy.game.math.Vector3f;

public class Light {

	public Vector3f position = new Vector3f();
	public Vector3f color = new Vector3f();
	
	public Light()
	{
		
	}
	
	public Light(Vector3f color)
	{
		this.color = color;
	}
	
	public Light(Vector3f color, Vector3f location)
	{
		this.color = color;
		position = location;
	}
}
