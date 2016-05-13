package com.colbyclardy.game.math;

public class Quaternion {

	//Finish the rest of this class
	
	public float x,y,z,w;
	
	public Quaternion()
	{
		x = 0;
		y = 0;
		z = 0;
		w = 0;
	}
	
	public Quaternion(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	public static Quaternion Identity()
	{
		return new Quaternion(0, 0, 0, 1);
	}
	
	public static Quaternion fromEulerAngels(Vector3f angles)
	{
		Quaternion pitch = new Quaternion(1, 0, 0, angles.x);
		Quaternion yaw = new Quaternion(0, 1, 0, angles.y);
		Quaternion roll = new Quaternion(0, 0, 1, angles.z);
		return pitch.multiply(yaw.multiply(roll));
	}
	
	public Vector3f getAxis()
	{
		float x = 1.0f - w * w;
		if(x < 0.0000001f)
			return new Vector3f(1, 0, 0);
		float x2 = x * x;
		return new Vector3f(x, y, z).divide(x2);
	}
	
	public Vector3f toEulerAngles()
	{
		return new Vector3f((float)Math.atan2(2 * x * w - 2 * y * z, 1 - 2 * x * x - 2 * z * z),
			(float)Math.atan2(2 * y * w - 2 * x * z, 1 - 2 * y * y - 2 * z * z),
			(float)Math.asin(2 * x * y + 2 * z * w));
	}
	
	public Quaternion multiply(float scalar)
	{
		return new Quaternion(x * scalar, y * scalar, z * scalar, w * scalar);
	}
	
	public static float Norm(Quaternion quaternion)
	{
		float result = 0;
		result = (quaternion.x * quaternion.x);
		result = (result + (quaternion.y * quaternion.y));
		result = (result + (quaternion.z * quaternion.z));
		result = (result + (quaternion.w * quaternion.w));
		return result;
	}
	
	public static float Length(Quaternion quaternion)
	{
		return (float)Math.sqrt(Norm(quaternion));
	}
	
	public static Quaternion Normalize(Quaternion quaternion)
	{
		float lenSqr, lenInv;
		lenSqr = Norm(quaternion);
		lenInv = (float)Math.sqrt(lenSqr) / lenSqr;
		return quaternion.multiply(lenInv);
	}
	
	public Quaternion multiply(Quaternion quat)
	{
		return Normalize(new Quaternion(
				(((w * quat.x) + (x * quat.w)) + (y * quat.z)) - (z * quat.y),
				(((w * quat.y) + (y * quat.w)) + (z * quat.x)) - (x * quat.z),
				(((w * quat.z) + (z * quat.w)) + (x * quat.y)) - (y * quat.x),
				(((w * quat.w) - (x * quat.x)) - (y * quat.y)) - (z * quat.z)
				));
	}
	
	public Quaternion Conjugate()
	{
		return new Quaternion(-x, -y, -z, w);
	}
	
	public float Dot(Quaternion other)
	{
		float result = x * other.x;
		result = (result + (y * other.y));
		result = (result + (z * other.z));
		result = (result + (w * other.w));
		return result;
	}
	
}
