package com.example.trial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{
	Button bu, bs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bu = (Button) findViewById(R.id.bu);
		bs = (Button) findViewById(R.id.bs);
		bu.setOnClickListener(this);
		bs.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
			case R.id.bu:
				Intent i = new Intent("com.example.trial.STARTINGACTIVITY");
				startActivity(i);
				break;
			case R.id.bs:
				Intent j = new Intent("com.example.trial.SLATE");
				startActivity(j);
				break;
		}
		
	}
	
}
