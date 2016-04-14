package com.colbyclardy.game.debug;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.colbyclardy.game.Game;
import com.colbyclardy.game.graphics.Window;

public class Debug {
	
	private static ArrayList<String> records = new ArrayList<>();
	
	private static int drawCalls = 0;
	
	private Debug()
	{
		
	}
	
	public static void Print(String input)
	{
		System.out.println("Debug: " + input);
		Record(input);
	}
	
	public static void Print(String name, String input)
	{
		System.out.println(name + ": " + input);
		Record(name + ": " + input);
	}
	
	public static void Log(String input)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat logFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			PrintWriter writer = new PrintWriter("log/log_" + dateFormat.format(date) + ".txt", "UTF-8");
			writer.println(Window.getTitle() + " Log - Dylan And Colby Games");
			writer.println('\n');
			writer.println("OpenGL: " + glGetString(GL_VERSION));
			//writer.println(Window.getTitle() + " version: " + Game.version);
			//writer.println("\n");
			writer.println("ERRORS:");
			writer.println(input);
			writer.println('\n');
			writer.println(logFormat.format(date));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Record(input);
	}
	
	public static void Log(String input, boolean crashed)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat logFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			PrintWriter writer = new PrintWriter("log/log_" + dateFormat.format(date) + ".txt", "UTF-8");
			writer.println(Window.getTitle() + " Log - Dylan And Colby Games");
			writer.println('\n');
			writer.println("OpenGL: " + glGetString(GL_VERSION));
			//writer.println(Game.title + " version: " + Game.version);
			//writer.println("\n");
			if(crashed)
				writer.println("Errors that caused crash:");
			writer.println(input);
			writer.println('\n');
			writer.println(logFormat.format(date));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Record(input);
	}
	
	public static void Record(String input)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss | MM/dd/yyyy | ");
		Date date = new Date();
		records.add(dateFormat.format(date) + input);
	}
	
	public static void LogRecords()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat logFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			PrintWriter writer = new PrintWriter("log/end_game_log_" + dateFormat.format(date) + ".log", "UTF-8");
			writer.println(Window.getTitle() + " Log - Dylan And Colby Games");
			writer.println('\n');
			writer.println("OpenGL: " + glGetString(GL_VERSION));
			//writer.println(Game.title + " version: " + Game.version);
			//writer.println("\n");
			writer.println("Game Logs:");
			writer.println("\n");
			for(int i = 0; i < records.size(); i++)
			{
				writer.println(records.get(i));
			}
			writer.println('\n');
			writer.println(logFormat.format(date));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static int getDrawCalls()
	{
		return drawCalls;
	}
	
	public static void addDrawCall()
	{
		drawCalls++;
	}
	
	public static void resetDrawCalls()
	{
		drawCalls = 0;
	}
	
}
