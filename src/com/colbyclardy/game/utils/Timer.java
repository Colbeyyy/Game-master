package com.colbyclardy.game.utils;

public class Timer {

	private long start;
	public double deltaTime;
	
	public Timer()
	{
		start = System.nanoTime();
	}
	
	public void reset()
	{
		start = System.nanoTime();
	}
	
	public double elapsed()
	{
		deltaTime = ((System.nanoTime() - start) / 10000000D) * 0.06D;
		return ((System.nanoTime() - start) / 10000000D) * 0.06D;
	}
	
}
