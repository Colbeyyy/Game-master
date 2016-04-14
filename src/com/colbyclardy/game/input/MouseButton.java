package com.colbyclardy.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButton extends GLFWMouseButtonCallback{

	public static boolean[] mouseButtons = new boolean[32];
	
	public void invoke(long window, int button, int action, int mods) {
		mouseButtons[button] = action != GLFW.GLFW_RELEASE;
	}
	
	public static boolean isMouseButtonDown(int button) {
		return mouseButtons[button];
	}

}
