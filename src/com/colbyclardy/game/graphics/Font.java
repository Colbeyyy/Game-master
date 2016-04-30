package com.colbyclardy.game.graphics;

import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.sprites.Spritesheet;

public class Font {

	private Texture[] characters;
	private Vector3f position;
	
	public Font(Spritesheet sheet, Vector3f position)
	{
		characters = new Texture[16 * 16];
		for(int y = 0; y < 16; y++)
		{
			for(int x= 0; x < 16; x++)
			{
				characters[x + y * 16] = new Texture(Spritesheet.fonts, 16, x, y);
			}
		}
		this.position = position;
	}
	
	public void render(String input, float size)
	{
		VertexArray mesh = VertexArray.createFontMesh(size);
		
		input = input.toUpperCase();
		
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == ' ')
			{
				continue;
			}
			Shader.UI.enable();
			Shader.UI.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(position.x + (i * size), position.y, position.z)));
			characters[(int)input.charAt(i) - 35].bind();
			mesh.render();
			characters[(int)input.charAt(i) - 35].unbind();
			Shader.UI.disable();
			
		}
	}
	
}
