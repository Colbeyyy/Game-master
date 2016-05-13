package com.colbyclardy.game.physics;

import com.colbyclardy.game.math.Quaternion;
import com.colbyclardy.game.math.Vector3f;

public class Transform {

	Vector3f position;
	Quaternion rotation;
	
	public Transform()
	{
		position = new Vector3f();
		rotation = new Quaternion();
	}
	
	public Transform(Vector3f position, Quaternion rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
}
