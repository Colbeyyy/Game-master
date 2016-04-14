package com.colbyclardy.game.input;

import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseWheel extends GLFWScrollCallback
{
	public static float mouseWheelx;
	public static float mouseWheely;
	
	public static boolean isScrolling = false;
	
	public void invoke(long window, double xoffset, double yoffset) {
		mouseWheelx = (float)xoffset;
		mouseWheely = (float)yoffset;
		isScrolling = true;
	}

}
