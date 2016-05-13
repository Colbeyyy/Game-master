package com.colbyclardy.game.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import com.colbyclardy.game.graphics.Window;

public class MouseInput extends GLFWCursorPosCallback{

	private static double mx, my;
	
	private static double mmx, mmy;
	
	private static double xpos,ypos;
	
	private static float lastX = Window.getDimensions().x / 2f;
	private static float lastY = Window.getDimensions().y / 2f;
	
	public void invoke(long window, double xpos, double ypos)
	{
		mmx = (xpos - lastX);
		mmy = (lastY - ypos);
		mx = xpos;
		my = ypos;
		lastX = (float)xpos;
		lastY = (float)ypos;	
		this.xpos = xpos;
		this.ypos = ypos;
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
	
	public static void update()
	{
		mmx = (xpos - lastX);
		mmy = (lastY - ypos);
	}
	
	
	
}
