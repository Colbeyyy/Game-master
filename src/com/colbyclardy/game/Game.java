package com.colbyclardy.game;

import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Window;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.tile.GrassTile;

public class Game {

	private Window window;
	private GrassTile tile;

	private void init() {
		window = new Window();
		tile = new GrassTile();
		Shader.loadAll();
		
		Matrix4f proj = Matrix4f.perspective(60, (float)window.getDimensions().x / window.getDimensions().y, 0.1f, 1000f);
		
		Shader.ENTITY.setUniformMat4f("pr_matrix", proj);
	}

	public void run() {
		while(window.closed())
		{
			window.clear();
			tile.render();
			tile.update();
			window.update();
		}
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.init();
		game.run();
	}
}
