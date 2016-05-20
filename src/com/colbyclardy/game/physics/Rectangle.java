package com.colbyclardy.game.physics;

import com.colbyclardy.game.math.Vector2f;
import com.colbyclardy.game.math.Vector3f;

public class Rectangle {

	public Vector2f position;
	public Vector2f size;
	
	public Rectangle()
	{
		position = new Vector2f();
		size = new Vector2f();
	}
	
	public Rectangle(Vector2f position, Vector2f size)
	{
		this.position = position;
		this.size = size;
	}
	
	public boolean contains(Vector2f position)
	{
		return false;
	}
	
	public boolean contains(Vector3f position)
	{
		return false;
	}
	
	public boolean contains(Rectangle other)
	{
		return false;
	}
	
	
}
