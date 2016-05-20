package com.colbyclardy.game.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.colbyclardy.game.graphics.VertexArray;
import com.colbyclardy.game.math.Vector3f;

public class OBJLoader {

	public static VertexArray loadModel(String filePath)
	{
		FileReader fr = null;
		try {
			fr = new FileReader(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		return null;
		
	}
	
}
