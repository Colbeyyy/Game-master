package com.colbyclardy.game.graphics;

import com.colbyclardy.game.physics.Transform;

public class Renderable {

	public Transform transform;
	
	public Renderable()
	{
		transform = new Transform();
	}
	
	public Renderable(Transform transform)
	{
		this.transform = transform;
	}
	
	
	
}
