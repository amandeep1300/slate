package com.example.trial;

import android.graphics.RectF;

public class Oval extends Shape {
	private RectF r;
	public postext area;
	private Point oneEndOfTheOval;
	public Oval(){
		super();
		r = new RectF(); // Create a new empty RectF.
		oneEndOfTheOval = new Point();
	}
	
        // getting the coordinates of the rectangle
	public RectF getRectangle(){
		return r;
	}
	
	public Point getoneEndOfTheOval(){
		return oneEndOfTheOval;
	}
	
	public void setoneEndOfTheOval(Point p){
		oneEndOfTheOval = p;
	}
}
