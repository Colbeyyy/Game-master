package com.colbyclardy.game.math;

public class Random {

	private Random()
	{
		
	}
	
	public static boolean getRandomBoolean()
	{
		int randTime = (int) (System.nanoTime() % 2);
		if(randTime == 0)
			return true;
		
		else
			return false;
	}
	
	public static int getRandomInt(int min, int max)
	{
		return (int) (System.nanoTime() % (max - min) + min);
	}
	
}
