package com.colbyclardy.game.tile;

import com.colbyclardy.game.debug.Debug;
import com.colbyclardy.game.graphics.Shader;
import com.colbyclardy.game.graphics.Texture;
import com.colbyclardy.game.graphics.VertexArray;
import com.colbyclardy.game.math.Matrix4f;
import com.colbyclardy.game.math.Vector3f;

public class Tile {

	public Vector3f position = new Vector3f();
	public VertexArray mesh;
	public Texture texture;
	
	public Vector3f rotation;
	
	public float SIZE = 1;
	
	public Tile()
	{
		float[] vertices = new float[] {
				-1.0f, -1.0f, 1.0f,//VO - 0
			    1.0f, -1.0f, 1.0f,//V1 - 1
			    -1.0f, 1.0f, 1.0f,//V2 - 2
			    1.0f, 1.0f, 1.0f,//V3 - 3

			    -1.0f, -1.0f, -1.0f,//V4 - 4
			    1.0f, -1.0f, -1.0f,//V5 - 5
			    -1.0f, 1.0f, -1.0f,//V6 - 6
			    1.0f, 1.0f, -1.0f,//V7 - 7

			    -1.0f, 1.0f, 1.0f,//V2 - 8
			    1.0f, 1.0f, 1.0f,//V3 - 9
			    -1.0f, 1.0f, -1.0f,//V6 - 10
			    1.0f, 1.0f, -1.0f,//V7 - 11

			    -1.0f, -1.0f, 1.0f,//VO - 12
			    1.0f, -1.0f, 1.0f,//V1 - 13
			    -1.0f, -1.0f, -1.0f,//V4 - 14
			    1.0f, -1.0f, -1.0f,//V5 - 15

			    -1.0f, -1.0f, 1.0f,//VO - 16
			    -1.0f, 1.0f, 1.0f,//V2 - 17
			    -1.0f, -1.0f, -1.0f,//V4 - 18
			    -1.0f, 1.0f, -1.0f,//V6 - 19

			    1.0f, -1.0f, 1.0f,//V1 - 20
			    1.0f, 1.0f, 1.0f,//V3 - 21
			    1.0f, -1.0f, -1.0f,//V5 - 22
			    1.0f, 1.0f, -1.0f,//V7 - 23
			};
				
			byte[] indices = new byte[] {
					0,1,2, 1,3,2,
				    6,7,5, 6,5,4,
				    8,9,10, 9,10,11,
				    12,13,14, 13,14,15,
				    17,16,18, 17,18,19,
				    20,21,22, 21,22,23
			};
			
			float[] tcs = new float[] {
					0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3

				    0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3

				    0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3

				    0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3

				    0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3

				    0.0f, 1.0f,//0
				    1.0f, 1.0f,//1
				    0.0f, 0.0f,//2
				    1.0f, 0.0f,//3
			};
			
		mesh = new VertexArray(vertices, indices, tcs);
		rotation = new Vector3f();
	}
	
	public Tile(Vector3f position)
	{
		float[] vertices = new float[] {
				-SIZE / 2.0f, -SIZE / 2.0f, 0.2f,
				-SIZE / 2.0f,  SIZE / 2.0f, 0.2f,
				 SIZE / 2.0f,  SIZE / 2.0f, 0.2f,
				 SIZE / 2.0f, -SIZE / 2.0f, 0.2f
			};
				
			byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
			};
			
			float[] tcs = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
			};
			
		this.position = position;
		
		mesh = new VertexArray(vertices, indices, tcs);
		rotation = new Vector3f();
	}
	
	public void render()
	{
		Shader.ENTITY.enable();
		Shader.ENTITY.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rotation.x, new Vector3f(1, 0 , 0))).multiply(Matrix4f.rotate(rotation.y, new Vector3f(0, 1 , 0)).multiply(Matrix4f.rotate(rotation.z, new Vector3f(0, 0, 1)))));
		Shader.ENTITY.setUniform1f("alpha", 0.5f);
		texture.bind();
		mesh.render();
		texture.unbind();
		Shader.ENTITY.disable();
		
		Debug.addDrawCall();
	}
	
	public void update()
	{
		rotation.x += 0.5f;
		rotation.y += 0.5f;
	}
		
}
