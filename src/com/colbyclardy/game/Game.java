package com.colbyclardy.game;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.graphics.Camera;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.math.Vector4f;
import com.colbyclardy.game.tile.GrassTile;
import com.colbyclardy.game.utils.Timer;

public class Game {

	private Window window;
	private GrassTile tile;
	private GrassTile tile2;
	private Camera cam;
	
	private GrassTile tiles[];
	
	public static float deltaTime;
	
	public static Timer timer;

	private void init() {
		window = new Window();
		tile = new GrassTile();
		tile2 = new GrassTile();
		cam = new Camera();
		timer = new Timer();
		tiles = new GrassTile[64 * 64];
		
		tile2.position = new Vector3f(5, 1, 5);
		
		for(int y = 0; y < 64; y++)
		{
			for(int x = 0; x < 64; x++)
			{
				tiles[x + y * 64] = new GrassTile();
				tiles[x + y * 64].position = new Vector3f(y, 0, x);
			}
		}
		
		Shader.loadAll();
		
		Matrix4f proj = Matrix4f.perspective(90, (float)window.getDimensions().x / window.getDimensions().y, 0.1f, 1000f);
		
		Shader.ENTITY.setUniformMat4f("pr_matrix", proj);
	}

	public void run() {
		while (window.closed()) {
			timer.reset();
			//tile.update();
			window.clear();
			for(int y = 0; y < 32; y++)
			{
				for(int x = 0; x < 32; x++)
				{
					tiles[x + y * 64].render();
				}
			}
			
			tile2.render();
			
			//Debug.Print(timer.deltaTime + ", " + deltaTime);
			float x = Math.round(cam.position.x);
			float z = Math.round(cam.position.z);	
			
			
			tile.position = new Vector3f(x,0,z);
			tile.render();
			
			cam.update();
			window.update();
			timer.elapsed();
		}
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.init();
		game.run();
	}
}
