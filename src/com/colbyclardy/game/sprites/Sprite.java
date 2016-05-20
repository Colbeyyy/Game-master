package com.colbyclardy.game.sprites;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Texture;
import com.colbyclardy.game.graphics.VertexArray;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;

public class Sprite {

	public Vector3f position = new Vector3f();
	public VertexArray mesh;
	public Texture texture;

	private float angle;

	public Sprite(float size, Texture texture) {
		mesh = generateMesh(size);
		this.texture = texture;
	}

	public VertexArray generateMesh(float size) {
		float[] vertices = new float[] {
				-size / 2.0f, -size / 2.0f, 0.2f, -size / 2.0f, size / 2.0f, 0.2f, size / 2.0f,
				size / 2.0f, 0.2f, size / 2.0f, -size / 2.0f, 0.2f };

		byte[] indices = new byte[] { 0, 1, 2, 2, 3, 0 };

		float[] tcs = new float[] { 0, 1, 0, 0, 1, 0, 1, 1 };

		return new VertexArray(vertices, indices, tcs, null);
	}

	public void update() {

	}

	public void render() {
		Shader.BILLBOARD.enable();
		Shader.BILLBOARD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(angle, new Vector3f(1, 0, 0))));
		Shader.BILLBOARD.setUniform1f("alpha", 1);
		texture.bind();
		mesh.render();
		Shader.BILLBOARD.disable();
		
		Debug.addDrawCall();
	}

}
