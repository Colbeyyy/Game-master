package com.colbyclardy.game.input;

import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector2f;

public class WindowResize extends GLFWWindowSizeCallback{

	public void invoke(long window, int width, int height) {
		glViewport(0, 0, width, height);
		Window.setDimensions(new Vector2f(width, height));
		
		glViewport(0, 0, width, height);
	}

}
