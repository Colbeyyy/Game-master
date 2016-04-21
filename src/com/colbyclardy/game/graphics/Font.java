package com.colbyclardy.game.graphics;

import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;
import com.colbyclardy.game.sprites.Spritesheet;

public class Font {

	private Texture[] letters;
	private Vector3f position;
	
	public Font(Spritesheet sheet, Vector3f position)
	{
		letters = new Texture[26];
		for(int i = 0; i < letters.length; i++)
		{
			letters[i] = new Texture(Spritesheet.sheet1, 8, i, 29);
		}
		this.position = position;
	}
	
	public void render(String input, float size)
	{
		VertexArray mesh = VertexArray.createFontMesh(size);
		
		input = input.toUpperCase();
		
		for(int i = 0; i < input.length(); i++)
		{
			Shader.UI.enable();
			Shader.UI.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(position.x + (i * size), position.y, position.z)));
			letters[(int)input.charAt(i) - 65].bind();
			mesh.render();
			letters[(int)input.charAt(i) - 65].unbind();
			Shader.UI.disable();
			
		}
	}
	
}
