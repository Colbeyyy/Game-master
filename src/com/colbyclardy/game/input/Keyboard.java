package com.colbyclardy.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import com.colbyclardy.game.debug.Debug;

public class Keyboard extends GLFWKeyCallback {
	
	public static boolean[] keys = new boolean[65536];
	public static boolean[] toggleKeys = new boolean[65536];

	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key == -1)
		{
			Debug.Print("Return: " + key);
			return;
		}
		
		if(key < keys.length);
			keys[key] = action != GLFW.GLFW_RELEASE; 
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
}
