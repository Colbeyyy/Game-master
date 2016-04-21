package com.colbyclardy.game;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.graphics.Camera;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.blocks.GrassBlock;
import com.colbyclardy.game.utils.Timer;

public class Game {

	private Window window;
	private GrassBlock tile;
	private GrassBlock tile2;
	private Camera cam;
	
	private GrassBlock tiles[];
	
	public static float deltaTime;
	
	public static Timer timer;

	private void init() {
		window = new Window();
		tile = new GrassBlock();
		tile2 = new GrassBlock();
		cam = new Camera();
		timer = new Timer();
		tiles = new GrassBlock[64 * 64];
		
		tile2.position = new Vector3f(5, 1, 5);
		
		for(int y = 0; y < 64; y++)
		{
			for(int x = 0; x < 64; x++)
			{
				tiles[x + y * 64] = new GrassBlock();
				tiles[x + y * 64].position = new Vector3f(y, 0, x);
			}
		}
		
		Shader.loadAll();
		
		Matrix4f proj = Matrix4f.perspective(60, (float)window.getDimensions().x / window.getDimensions().y, 0.1f, 1000f);
		
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
			
			char test = 'u';
			Debug.Print((int)test + "");
		}
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.init();
		game.run();
	}
}
