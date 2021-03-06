package com.example.trial;


import com.example.trial.Point;
import com.example.trial.Shape;

public class Circle extends Shape {
	
	private float centerX;
	private float centerY;
	private float radius;
	private Point oneEndofTheCircle;
	public postext area;
	//private Point anotherEndOfTheCircle;
	
	public Circle(){
		super();
		centerX = 0;
		centerY = 0;
		radius = 0;
		oneEndofTheCircle = new Point();
	}
	// used to get x point
	public float getCenterX()
	{
		return centerX;
	}
	
     // used to get y point
	public float getCenterY()
	{
		return centerY;
	}
	
    // used to get radius
	public float getRadius()
	{
		return radius;
	}
	public void setCenterX(float x)
	{
		centerX = x;
	}
	public void setCenterY(float y)
	{
		centerY = y;
	}

      // used to set radius
	public void setReadius(float r)
	{
		radius = r;
	}
	public void setOneEndOfTheCircle(Point p)
	{
		oneEndofTheCircle = p;
	}
	public Point getOneEndOfTheCircle()
	{
		return oneEndofTheCircle;
	}
	

}
