package com.colbyclardy.game.math;

public class Vector2f {

	public float x, y;
	
	public Vector2f()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void divide(Vector2f vec)
	{
		this.x = this.x / vec.x;
		this.y = this.y / vec.y;
	}
	
	public void multiply(Vector2f vec)
	{
		this.x = this.x * vec.x;
		this.y = this.y * vec.y;
	}
	
	public void multiplyf(float f)
	{
		this.x *= f;
		this.y *= f;
	}
	
}
