package com.colbyclardy.game.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.input.Keyboard;
import com.colbyclardy.game.input.MouseInput;
import com.colbyclardy.game.input.WindowResize;
import com.colbyclardy.game.math.Vector2f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.sprites.Spritesheet;

public class Window {

	private static int width;
	private static int height;
	private static String title;
	
	public static boolean debug;
	
	private boolean debugButton;
	private boolean reloadTextureButton;
	
	private GLFWKeyCallback keyCallback;
	private GLFWCursorPosCallback mousePosCallback;
	private GLFWWindowSizeCallback windowResize;
	
	private Font font;
	
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
		glfwSetWindowSizeCallback(window, windowResize = new WindowResize());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		//glfwSwapInterval(1);
		GL.createCapabilities();
		
		glEnable(GL_DEPTH_TEST);
		//glDepthFunc()
		//glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		//glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
		glDepthFunc(GL_LESS);
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED); 
		
		glClearColor(0.4f, 0.7f, 1f, 1f);
		font = new Font(Spritesheet.sheet1, new Vector3f(-0.725f, 0.35f, 0));
	}
	
	public void update()
	{
		if(debug)
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			font.render("Paused", 1f);
			
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		}
		int error = glGetError();
		if(error != GL_NO_ERROR)
		{
			System.out.println("OpenGL Error");
		}
		
		if(Keyboard.isKeyDown(GLFW_KEY_F3) || Keyboard.isKeyDown(GLFW_KEY_ESCAPE))
		{
			if(!debugButton)
			{
				debugButton = true;
				debug = !debug;
				if(debug)
				{
					glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
					glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL); 
				}
				else
				{
					glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
					glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED); 
				}
			}
		}
		else
		{
			debugButton = false;
		}
		
		if(Keyboard.isKeyDown(GLFW_KEY_F2))
		{
			if(!reloadTextureButton)
			{
				reloadTextureButton = true;
				Texture.loadAll();
			}
		}
		else
		{
			reloadTextureButton = false;
		}
		
		
		MouseInput.update();
		
		
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
	
	public static void setDimensions(Vector2f dimensions)
	{
		width = (int) dimensions.x;
		height = (int) dimensions.y;
	}
	
}
