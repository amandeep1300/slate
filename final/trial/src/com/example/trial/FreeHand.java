package com.example.trial;

import java.util.ArrayList;

import android.graphics.Path;
import com.example.trial.Shape;

// In this code its tracing the path drawn using hand
public class FreeHand extends Shape{
	private ArrayList<Path>_graphics;
	
	public FreeHand(){
		super();
		 _graphics = new ArrayList<Path>();
	}
	
	public ArrayList<Path> getGraphicsPath(){
		return _graphics;
	}

}
