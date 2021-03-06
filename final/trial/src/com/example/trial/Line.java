package com.example.trial;


import com.example.trial.Shape;
import android.graphics.Path;

public class Line extends Shape{
	private Point begin;// used for calculating begining point
    private Point end;// used for calculating end point
    public postext area;
    public Line(){
    	super();
    	begin = new Point();
    	end = new Point();	
    }
    public Line(Point begin_point, Point end_point){
    	super();
	    begin = begin_point;
	    end = end_point;
    //path_of_shape.moveTo(begin.getX(), begin.getY());
    //path_of_shape.lineTo(end.getX(),end.getY());
    }
    
    // Assign a new path, or null to have none. 
    public void setPath(Path path){
    	path_of_shape = path;
    }
    
    public Point getBegin(){
    	return begin; // returning the begining point
    }
    
    public Point getEnd(){
    	return end; // returning the end point
    }

    // setting the begining point
    public void setBegin(Point _begin){
    begin = _begin;	
    }
    

     // setting the end point
    public void setEnd(Point _end){
    	end = _end;
    }
}
