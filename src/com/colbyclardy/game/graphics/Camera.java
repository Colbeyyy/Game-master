package com.colbyclardy.game.graphics;

import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;

public class Camera {

	public Vector3f position;
	public Vector3f rotation;
	
	public Camera()
	{
		position = new Vector3f();
		rotation = new Vector3f();
	}
	
	public void update()
	{
		//position.z += 0.005f;
		rotation.y += 0.05f;
		Shader.ENTITY.enable();
		Shader.ENTITY.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(-position.x, -position.y, -position.z)).multiply(Matrix4f.rotate(rotation.x, new Vector3f(1, 0 , 0))).multiply(Matrix4f.rotate(rotation.y, new Vector3f(0, 1 , 0)).multiply(Matrix4f.rotate(rotation.z, new Vector3f(0, 0, 1)))));
		Shader.ENTITY.disable();
	}
}
