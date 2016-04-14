package com.colbyclardy.game.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseInput extends GLFWCursorPosCallback{

	private static double mx, my;
	
	private static double mmx, mmy;
	
	public void invoke(long window, double xpos, double ypos)
	{
		mmx = mx - xpos;
		mmy = my - ypos;
		mx = xpos;
		my = ypos;
	}
	
	public static double getMouseX()
	{
		return mx;
	}
	
	public static double getMouseY()
	{
		return my;
	}
	
	public static double getMouseMoveX()
	{
		return mmx;
	}
	
	public static double getMouseMoveY()
	{
		return mmy;
	}
	
}
