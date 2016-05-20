package com.colbyclardy.game;

import com.colbyclardy.game.blocks.GrassBlock;
import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.graphics.Camera;
import com.colbyclardy.game.graphics.Font;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Texture;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.sprites.Sprite;
import com.colbyclardy.game.sprites.Spritesheet;
import com.colbyclardy.game.utils.Timer;

public class Game {

	private Window window;
	private GrassBlock tile;
	private GrassBlock tile2;
	private Camera cam;
	private Font font;
	
	private GrassBlock tiles[];
	private Sprite sprite;
	
	public static float deltaTime;
	
	public static Timer timer;

	private void init() {
		window = new Window();
		Texture.loadAll();
		tile = new GrassBlock();
		tile2 = new GrassBlock();
		cam = new Camera();
		timer = new Timer();
		tiles = new GrassBlock[64 * 64];
		sprite = new Sprite(5, Texture.dirt);
		font = new Font(Spritesheet.sheet1, new Vector3f(-0.725f, 0.35f, 0));
		
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
		
		Matrix4f ortho = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
		Matrix4f proj = Matrix4f.perspective(60, (float)window.getDimensions().x / window.getDimensions().y, 0.1f, 1000f);
		
		Shader.ENTITY.setUniformMat4f("pr_matrix", proj);
		Shader.UI.setUniformMat4f("pr_matrix", ortho);
		Shader.BILLBOARD.setUniformMat4f("pr_matrix", proj);
	}

	public void run() {
		float timers = 0;
		int frames = 0;
		int fps = 0;
		while (window.closed()) {
			timer.reset();
			//tile.update();
			window.clear();
			for(int y = 0; y < 16; y++)
			{
				for(int x = 0; x < 16; x++)
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
			//sprite.render();
			//font.render("FPS: " + fps, .4f);
			
			cam.update();
			window.update();
			fps = (int)(1 / timer.elapsed());
			
		}
		end();
		
	}
	
	private void end()
	{
		Shader.killAll();
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.init();
		game.run();
	}
}
