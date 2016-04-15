package com.colbyclardy.game.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import com.colbyclardy.game.Game;
import com.colbyclardy.game.input.Keyboard;
import com.colbyclardy.game.input.MouseInput;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;

public class Camera {

	public Vector3f position;
	public Vector3f rotation;
	
	public float speed = 0.005f;
	
	public Camera()
	{
		position = new Vector3f();
		rotation = new Vector3f();
		position.y = 5;
	}
	
	public void update()
	{
		//position.z += 0.005f;
		if(Keyboard.isKeyDown(GLFW_KEY_W))
		{
			position.z -= (float) Math.cos(Math.toRadians(rotation.y % 360)) * speed;
			position.x += (float) Math.sin(Math.toRadians(rotation.y % 360)) * speed;
		}
		
		if(Keyboard.isKeyDown(GLFW_KEY_A))
		{
			position.x -= (float) Math.cos(Math.toRadians(rotation.y % 360)) * speed;
			position.z -= (float) Math.sin(Math.toRadians(rotation.y % 360)) * speed;
		}
		
		if(Keyboard.isKeyDown(GLFW_KEY_S))
		{
			position.z += (float) Math.cos(Math.toRadians(rotation.y % 360)) * speed;
			position.x -= (float) Math.sin(Math.toRadians(rotation.y % 360)) * speed;
		}
		
		if(Keyboard.isKeyDown(GLFW_KEY_D))
		{
			position.x += (float) Math.cos(Math.toRadians(rotation.y % 360)) * speed;
			position.z += (float) Math.sin(Math.toRadians(rotation.y % 360)) * speed;
		}
		
		if(!Window.debug)
		{
			rotation.y += (float)MouseInput.getMouseMoveX() * 0.05f;
			rotation.x -= (float)MouseInput.getMouseMoveY() * 0.05f;
		}
		
		if(rotation.x >= 80f)
		{
			rotation.x = 80f;
		}
		
		if(rotation.x <= -80f)
		{
			rotation.x = -80f;
		}
		
		//rotation.y += 0.05f;
		Matrix4f view = Matrix4f.translate(new Vector3f(-0.2f, 0, 0)).multiply(Matrix4f.rotate(rotation.x, new Vector3f(1, 0, 0)).multiply(Matrix4f.rotate(rotation.y, new Vector3f(0, 1, 0)).multiply(Matrix4f.rotate(rotation.z, new Vector3f(0, 0, 1)))));
		
		Shader.ENTITY.enable();
		Shader.ENTITY.setUniformMat4f("vw_matrix", view.multiply(Matrix4f.translate(new Vector3f(-position.x, -position.y, -position.z))));
		Shader.ENTITY.disable();
	}
	
	public Vector3f getFront()
	{
		Vector3f result = new Vector3f();
		result.y = (float) (Math.cos((double)Math.toRadians(rotation.y)) * Math.cos((double)Math.toRadians(rotation.x)));
		result.x = (float) Math.sin(Math.toRadians((float)rotation.x));
		result.z = (float) (Math.sin((double)Math.toRadians(rotation.y)) * Math.cos((double)Math.toRadians(rotation.x)));
		return result.normalize();
	}
}
