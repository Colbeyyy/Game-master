package com.colbyclardy.game.math;

public class Vector3f {

	public float x,y,z;
	
	public Vector3f()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f normalize()
	{
		float dist = (float)Math.sqrt((double)((x * x) + (y * y) + (z * z)));
		
		x = (float) (x * (1.0 / dist));
		y = (float) (y * (1.0 / dist));
		z = (float) (z * (1.0 / dist));
		
		return this;
	}
	
	public Vector3f add(Vector3f vec)
	{
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}
	
	public void subtract(Vector3f other)
	{
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
	}
	
	public Vector3f multiply(float f)
	{
		this.x *= f;
		this.y *= f;
		this.z *= f;
		
		return this;
	}
	
	public Vector3f divide(float f)
	{
		return new Vector3f(
				x / f,
				y / f,
				z / f
				);
	}
	
}