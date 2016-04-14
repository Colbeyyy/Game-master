package com.colbyclardy.game.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.colbyclardy.game.input.Keyboard;
import com.colbyclardy.game.input.MouseInput;
import com.colbyclardy.game.math.Vector2f;

public class Window {

	private static int width;
	private static int height;
	private static String title;
	
	private GLFWKeyCallback keyCallback;
	private GLFWCursorPosCallback mousePosCallback;
	
	private long window;
	
	public Window()
	{
		width = 1080;
		height = width / 16 * 9;
		title = "Game";
		init();
	}
	
	public Window(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		init();
	}
	
	private void init()
	{
		if(glfwInit() != GL_TRUE)
		{
			System.out.println("GLFW could not init");
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL) {
			System.out.println("Window could not be created");
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		
		glfwSetKeyCallback(window, keyCallback = new Keyboard());
		glfwSetCursorPosCallback(window, mousePosCallback = new MouseInput());
		//glfwSetWindowResizeCallback(window, )
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GL.createCapabilities();
		
		glEnable(GL_DEPTH_TEST);
		//glDepthFunc()
		//glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
		glDepthFunc(GL_LESS);
		
		glClearColor(0.2f, 0.2f, 0.2f, 1);
	}
	
	public void update()
	{
		int error = glGetError();
		if(error != GL_NO_ERROR)
		{
			System.out.println("OpenGL Error");
		}
		
		
		glfwSwapBuffers(window);
		
		glfwPollEvents();
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public boolean closed()
	{
		return glfwWindowShouldClose(window) != GL_TRUE;
	}
	
	public long getWindow()
	{
		return window;
	}
	
	public static String getTitle()
	{
		return title;
	}
	
	public static Vector2f getDimensions()
	{
		return new Vector2f(width, height);
	}
	
}
