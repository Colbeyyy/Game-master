package com.colbyclardy.game;

import com.colbyclardy.game.graphics.Camera;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.tile.GrassTile;
import com.colbyclardy.game.utils.Timer;

public class Game {

	private Window window;
	private GrassTile tile;
	private Camera cam;
	
	private GrassTile tiles[];
	
	public static Timer timer;

	private void init() {
		window = new Window();
		tile = new GrassTile();
		cam = new Camera();
		timer = new Timer();
		tiles = new GrassTile[8 * 8];
		
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 8; x++)
			{
				tiles[x + y * 8] = new GrassTile();
				tiles[x + y * 8].position = new Vector3f(y * 2, 0, x * 2);
			}
		}
		
		Shader.loadAll();
		
		Matrix4f proj = Matrix4f.perspective(90, (float)window.getDimensions().x / window.getDimensions().y, 0.1f, 1000f);
		
		Shader.ENTITY.setUniformMat4f("pr_matrix", proj);
	}

	public void run() {
		while(window.closed())
		{
			timer.reset();
			window.clear();
			tile.render();
			tile.update();
			
			for(int y = 0; y < 8; y++)
			{
				for(int x = 0; x < 8; x++)
				{
					tiles[x + y * 8].render();
				}
			}
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
