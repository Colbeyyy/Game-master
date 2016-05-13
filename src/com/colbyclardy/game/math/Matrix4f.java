package com.colbyclardy.game.math;

import java.nio.FloatBuffer;

import com.colbyclardy.game.utils.BufferUtils;

public class Matrix4f {
	public static final int SIZE = 4 * 4;
	public float[] elements = new float[SIZE];
	
	public Matrix4f() {
		
	}
	
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < SIZE; i++) {
			result.elements[i] = 0.0f;
		}
		result.elements[0 + 0 * 4] = 1.0f;
		result.elements[1 + 1 * 4] = 1.0f;
		result.elements[2 + 2 * 4] = 1.0f;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
	}
	
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (right - left);

		result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

		result.elements[2 + 2 * 4] = 2.0f / (near - far);
		
		result.elements[0 + 3 * 4] = (left + right) / (left - right);
		result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * 4] = (far + near) / (far - near);
		
		return result;
	}
	
	public static Matrix4f perspective(float fov, float aspectRation, float near, float far)
	{
		Matrix4f result = identity();
		float q = 1.0f / (float)Math.tan((float)Math.toRadians(0.5f * fov));
		float a = q / aspectRation;
		float b = (near + far) / (near - far);
		float c = (2 * near * far) / (near - far);
		
		result.elements[0 + 0 * 4] = a;
		result.elements[1 + 1 * 4] = q;
		result.elements[2 + 2 * 4] = b;
		result.elements[3 + 2 * 4] = -1;
		result.elements[2 + 3 * 4] = c;
		
		return result;
	}
	
	public static Matrix4f translate(Vector3f vector) {
		Matrix4f result = identity();
		result.elements[0 + 3 * 4] = vector.x;
		result.elements[1 + 3 * 4] = vector.y;
		result.elements[2 + 3 * 4] = vector.z;
		return result;
	}
	
	public static Matrix4f rotate(float angle, Vector3f axis) {
		Matrix4f result = identity();
		float r = (float) Math.toRadians(angle);
		float c = (float) Math.cos(r);
		float s = (float) Math.sin(r);
		float omc = 1 - c;
		
		float x = axis.x;
		float y = axis.y;
		float z = axis.z;
		result.elements[0 + 0 * 4] = x * omc + c;
		result.elements[1 + 0 * 4] = y * x * omc + z * s;
		result.elements[2 + 0 * 4] = x * z * omc - y * s;
		result.elements[0 + 1 * 4] = x * y * omc - z * s;
		result.elements[1 + 1 * 4] = y * omc + c;
		result.elements[2 + 1 * 4] = y * z * omc + x * s;
		result.elements[0 + 2 * 4] = x * z * omc + y * s;
		result.elements[1 + 2 * 4] = y * z * omc - x * s;
		result.elements[2 + 2 * 4] = z * omc + c;
		
		return result;
	}
	
	public static Matrix4f rotate(Quaternion quat)
	{
		Matrix4f result = Matrix4f.identity();

		float qx, qy, qz, qw, qx2, qy2, qz2, qxqx2, qyqy2, qzqz2, qxqy2, qyqz2, qzqw2, qxqz2, qyqw2, qxqw2;
		qx = quat.x;
		qy = quat.y;
		qz = quat.z;
		qw = quat.w;
		qx2 = (qx + qx);
		qy2 = (qy + qy);
		qz2 = (qz + qz);
		qxqx2 = (qx * qx2);
		qxqy2 = (qx * qy2);
		qxqz2 = (qx * qz2);
		qxqw2 = (qw * qx2);
		qyqy2 = (qy * qy2);
		qyqz2 = (qy * qz2);
		qyqw2 = (qw * qy2);
		qzqz2 = (qz * qz2);
		qzqw2 = (qw * qz2);
		
		result.setRow(0, new Vector4f(((1.0f - qyqy2) - qzqz2), (qxqy2 - qzqw2), (qxqz2 + qyqw2), 0.0f));
		result.setRow(1, new Vector4f((qxqy2 + qzqw2), ((1.0f - qxqx2) - qzqz2), (qyqz2 - qxqw2), 0.0f));
		result.setRow(2, new Vector4f((qxqz2 - qyqw2), (qyqz2 + qxqw2), ((1.0f - qxqx2) - qyqy2), 0.0f));
		return result;
	}
	
	public void setRow(int row, Vector4f other)
	{
		elements[0 + row * 4] = other.x;
		elements[1 + row * 4] = other.y;
		elements[2 + row * 4] = other.z;
		elements[3 + row * 4] = other.w;
	}
	
	public Vector4f multiply(Vector4f vec)
	{
		Vector4f result = new Vector4f();
		
		for(int x = 0; x < 4; x++)
		{
			float sum = 0;
			for(int y = 0; y < 4; y++)
			{
				if(x == 0)
				{
					sum += this.elements[x + y * 4] * vec.x;
				}
				else if(x == 1)
				{
					sum += this.elements[x + y * 4] * vec.y;
				}
				else if(x == 2)
				{
					sum += this.elements[x + y * 4] * vec.z;
				}
				else if(x == 3)
				{
					sum += this.elements[x + y * 4] * vec.w;
				}
			}
			if(x == 0)
			{
				result.x = sum;
			}
			else if(x == 1)
			{
				result.y = sum;
			}
			else if(x == 2)
			{
				result.z = sum;
			}
			else if(x == 3)
			{
				result.w = sum;
			}
		}
		
		return result;
	}
	
	public Matrix4f multiply(Matrix4f matrix) {
		Matrix4f result = new Matrix4f();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				float sum = 0.0f;
				for (int e = 0; e < 4; e++) {
					sum += this.elements[x + e * 4] * matrix.elements[e + y * 4]; 
				}			
				result.elements[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	public Matrix4f inverse(){
		Matrix4f result = new Matrix4f();
		result.elements[0] = elements[5]  * elements[10] * elements[15] - 
	             elements[5]  * elements[11] * elements[14] - 
	             elements[9]  * elements[6]  * elements[15] + 
	             elements[9]  * elements[7]  * elements[14] +
	             elements[13] * elements[6]  * elements[11] - 
	             elements[13] * elements[7]  * elements[10];

	    result.elements[4] = -elements[4]  * elements[10] * elements[15] + 
	              elements[4]  * elements[11] * elements[14] + 
	              elements[8]  * elements[6]  * elements[15] - 
	              elements[8]  * elements[7]  * elements[14] - 
	              elements[12] * elements[6]  * elements[11] + 
	              elements[12] * elements[7]  * elements[10];

	    result.elements[8] = elements[4]  * elements[9] * elements[15] - 
	             elements[4]  * elements[11] * elements[13] - 
	             elements[8]  * elements[5] * elements[15] + 
	             elements[8]  * elements[7] * elements[13] + 
	             elements[12] * elements[5] * elements[11] - 
	             elements[12] * elements[7] * elements[9];

	    result.elements[12] = -elements[4]  * elements[9] * elements[14] + 
	               elements[4]  * elements[10] * elements[13] +
	               elements[8]  * elements[5] * elements[14] - 
	               elements[8]  * elements[6] * elements[13] - 
	               elements[12] * elements[5] * elements[10] + 
	               elements[12] * elements[6] * elements[9];

	    result.elements[1] = -elements[1]  * elements[10] * elements[15] + 
	              elements[1]  * elements[11] * elements[14] + 
	              elements[9]  * elements[2] * elements[15] - 
	              elements[9]  * elements[3] * elements[14] - 
	              elements[13] * elements[2] * elements[11] + 
	              elements[13] * elements[3] * elements[10];

	    result.elements[5] = elements[0]  * elements[10] * elements[15] - 
	             elements[0]  * elements[11] * elements[14] - 
	             elements[8]  * elements[2] * elements[15] + 
	             elements[8]  * elements[3] * elements[14] + 
	             elements[12] * elements[2] * elements[11] - 
	             elements[12] * elements[3] * elements[10];

	    result.elements[9] = -elements[0]  * elements[9] * elements[15] + 
	              elements[0]  * elements[11] * elements[13] + 
	              elements[8]  * elements[1] * elements[15] - 
	              elements[8]  * elements[3] * elements[13] - 
	              elements[12] * elements[1] * elements[11] + 
	              elements[12] * elements[3] * elements[9];

	    result.elements[13] = elements[0]  * elements[9] * elements[14] - 
	              elements[0]  * elements[10] * elements[13] - 
	              elements[8]  * elements[1] * elements[14] + 
	              elements[8]  * elements[2] * elements[13] + 
	              elements[12] * elements[1] * elements[10] - 
	              elements[12] * elements[2] * elements[9];

	    result.elements[2] = elements[1]  * elements[6] * elements[15] - 
	             elements[1]  * elements[7] * elements[14] - 
	             elements[5]  * elements[2] * elements[15] + 
	             elements[5]  * elements[3] * elements[14] + 
	             elements[13] * elements[2] * elements[7] - 
	             elements[13] * elements[3] * elements[6];

	    result.elements[6] = -elements[0]  * elements[6] * elements[15] + 
	              elements[0]  * elements[7] * elements[14] + 
	              elements[4]  * elements[2] * elements[15] - 
	              elements[4]  * elements[3] * elements[14] - 
	              elements[12] * elements[2] * elements[7] + 
	              elements[12] * elements[3] * elements[6];

	    result.elements[10] = elements[0]  * elements[5] * elements[15] - 
	              elements[0]  * elements[7] * elements[13] - 
	              elements[4]  * elements[1] * elements[15] + 
	              elements[4]  * elements[3] * elements[13] + 
	              elements[12] * elements[1] * elements[7] - 
	              elements[12] * elements[3] * elements[5];

	    result.elements[14] = -elements[0]  * elements[5] * elements[14] + 
	               elements[0]  * elements[6] * elements[13] + 
	               elements[4]  * elements[1] * elements[14] - 
	               elements[4]  * elements[2] * elements[13] - 
	               elements[12] * elements[1] * elements[6] + 
	               elements[12] * elements[2] * elements[5];

	    result.elements[3] = -elements[1] * elements[6] * elements[11] + 
	              elements[1] * elements[7] * elements[10] + 
	              elements[5] * elements[2] * elements[11] - 
	              elements[5] * elements[3] * elements[10] - 
	              elements[9] * elements[2] * elements[7] + 
	              elements[9] * elements[3] * elements[6];

	    result.elements[7] = elements[0] * elements[6] * elements[11] - 
	             elements[0] * elements[7] * elements[10] - 
	             elements[4] * elements[2] * elements[11] + 
	             elements[4] * elements[3] * elements[10] + 
	             elements[8] * elements[2] * elements[7] - 
	             elements[8] * elements[3] * elements[6];

	    result.elements[11] = -elements[0] * elements[5] * elements[11] + 
	               elements[0] * elements[7] * elements[9] + 
	               elements[4] * elements[1] * elements[11] - 
	               elements[4] * elements[3] * elements[9] - 
	               elements[8] * elements[1] * elements[7] + 
	               elements[8] * elements[3] * elements[5];

	    result.elements[15] = elements[0] * elements[5] * elements[10] - 
	              elements[0] * elements[6] * elements[9] - 
	              elements[4] * elements[1] * elements[10] + 
	              elements[4] * elements[2] * elements[9] + 
	              elements[8] * elements[1] * elements[6] - 
	              elements[8] * elements[2] * elements[5];

		return result;
	}
	
	public Matrix4f scale(Vector3f scale)
	{
		Matrix4f result = identity();
		result.elements[0 + 0 * 4] = scale.x;
		result.elements[1 + 1 * 4] = scale.y;
		result.elements[2 + 2 * 4] = scale.z;
		
		return result;
	}
	
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elements);
	}
	
	public String toString()
	{
		String result = "lol";
	
		return result;
	}
}
