package com.example.trial;


import android.graphics.Path;

public class Shape extends Object{
	
			//private Paint paint;
			private int[] color_rgb;
			public Path path_of_shape;
			protected float strokeWidth;
	    	
			public Shape(){
	    		//paint = new Paint();
	    		path_of_shape = new Path();
	    		color_rgb = new int[3];
	    		color_rgb[0] = 0;
	    		color_rgb[1] = 0;
	    		color_rgb[2] = 0;
	    		strokeWidth = 3;
	    	}
	    	
	    	
	    	public Path getPath(){
	    		return path_of_shape;
	    	}
	    	
	    	public void setPath(Path p){
	    		path_of_shape = p;
	    	}
	    	public void reset()
	    	{
	    		path_of_shape=new Path();
	    	}
	    	 
               // here we are setting the color 
	    	public void setrgb(int red, int green, int blue){
	    		color_rgb[0] = red;
	    		color_rgb[1] = green;
	    		color_rgb[2] = blue;
	    	}
	    	
	    	public int[] getrgb(){
	    		return color_rgb;
	    	}
                 
  	    	// setting the stroke width
	    	public void setStrokeWidth(float w){
	    		strokeWidth = w;
	    	}
	    	
	    	public float getStrokeWidth(){
	    		return strokeWidth;
	    	}
}
