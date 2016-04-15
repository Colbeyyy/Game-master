package com.colbyclardy.game.utils;

public class Timer {

	private long start;
	
	public Timer()
	{
		start = System.currentTimeMillis();
	}
	
	public void reset()
	{
		start = System.currentTimeMillis();
	}
	
	public float elapsed()
	{
		return (float)((System.currentTimeMillis() - start) / 1000f);
	}
	
}
