package com.colbyclardy.game.input;

import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import com.colbyclardy.game.Game;

public class WindowResize extends GLFWWindowSizeCallback{

	public void invoke(long window, int width, int height) {
		glViewport(0, 0, width, height);
		//Game.width = width;
		//Game.height = height;
	}

}
