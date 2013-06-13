package com.example.trial;

import com.example.trial.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;






import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class MainActivity extends Activity {
  
	static final int REQUEST_CODE = 1001;
	private Shape currentGraphicObject;
	private int ShapeObject_to_be_created;
	private static final int ShapeLine = 1;
	private static final int ShapeRect = 2;
	private static final int ShapeCircle = 3;
	private static final int ShapeOval = 4;
	private static final int ShapeFreehand =5;
	private static final int ShapeErase = 6;
	private Paint mPaint;
	private Menu mMenu = null;
	private float BrushWidth;
	double[] color;
	Panel p;
	int number_of_graphicObjects;
	boolean shapemenuclicked;
	boolean colormenuclicked;
	boolean brushwidthmenuclicked;
	private ArrayList<Shape> graphicobjects;
	static String mImagePath;
	File file;
	File tempFile;
	Canvas bitmapCanvas;
	String savedFilePath = "";
	static String mSavedTempFilePath = "";
	private boolean isFileAlreadySaved = false;
	
	private boolean willTheTempFileForExistingImage = false;
	private String theOriginalFile = "";
	
	Bitmap bitmap;
	Button expander,save,exit, shapes,draw,erase,setting,eraseall,changeBcolor,changePcolor,changePwidth,goback,next,prev,deletepage;
	Button circle,rectangle,line,oval,freehand;
	Boolean isexpanded;
	FrameLayout fl;
	int expl1,expl2;
    SoundPool sp1,sp2;
    int screenWidth,screenHeight;
    int mainbuttonsize,layoutsize;
    RelativeLayout l1,il1,il2,il3,il4,l2,iil1,iil2,iil3,iil4,iil5,iil6,iil7,iil8,iil9;
    Boolean checkb,checkd;
    
    private ArrayList<postext> coveredarea;
    public postext thisarea,checkcontain;
    Boolean flag;
    TextView t1;
    int checkColor ;
    int mode;
    boolean wasClickedInside;
    int displacementX,displacementY;
    int preDisplacementX,preDisplacementY;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        
        color = new double[3];
        mode=0;
        t1=new TextView(this);
        wasClickedInside=false;
        graphicobjects = new ArrayList<Shape>();
        coveredarea=new ArrayList<postext>();
        currentGraphicObject = new Shape();
        thisarea=new postext();
        preDisplacementX=0;
        preDisplacementY=0;
        
        mPaint = new Paint();
        InitializePaint();
        shapemenuclicked = false;
        colormenuclicked = false;
        checkb=false;
        checkd=false;
        flag= false;
        BrushWidth = 3;
        isexpanded=true;
        checkColor     = 4;
        sp1=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        expl1=sp1.load(getBaseContext(), R.raw.move, 1);
        sp2=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        expl2=sp2.load(getBaseContext(), R.raw.click, 1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight=metrics.heightPixels;
       
        
        layoutsize=(int) (screenWidth*1/9);
        mainbuttonsize=layoutsize-50;
        RelativeLayout.LayoutParams p1 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        
        RelativeLayout.LayoutParams p2 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        
        RelativeLayout.LayoutParams p3 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p3.addRule(RelativeLayout.CENTER_VERTICAL);
        
        RelativeLayout.LayoutParams p4 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        
        RelativeLayout.LayoutParams p5 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p5.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        
        RelativeLayout.LayoutParams p6 =new RelativeLayout.LayoutParams(mainbuttonsize,mainbuttonsize);
        p6.addRule(RelativeLayout.CENTER_HORIZONTAL);
        l1=new RelativeLayout(this);
        l1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,mainbuttonsize));
        
        //l2=new RelativeLayout(this);
      //  l2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,mainbuttonsize));
        
       
        il1=new RelativeLayout(this);
        RelativeLayout.LayoutParams pt1 =new RelativeLayout.LayoutParams(3*layoutsize,layoutsize);
        pt1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        il1.setLayoutParams(pt1);
        
        il2=new RelativeLayout(this);
        RelativeLayout.LayoutParams pt2 =new RelativeLayout.LayoutParams(3*layoutsize,layoutsize);
        pt2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        il2.setLayoutParams(pt2);
        
        il3=new RelativeLayout(this);
        RelativeLayout.LayoutParams pt3 =new RelativeLayout.LayoutParams(3*layoutsize,layoutsize);
        pt3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        il3.setLayoutParams(pt3);
        
        RelativeLayout.LayoutParams pt11 =new RelativeLayout.LayoutParams(layoutsize,layoutsize);
        pt11.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout.LayoutParams pt22 =new RelativeLayout.LayoutParams(layoutsize,layoutsize);
        pt22.addRule(RelativeLayout.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams pt33 =new RelativeLayout.LayoutParams(layoutsize,layoutsize);
        pt33.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        
       
        fl = new FrameLayout(this);  
        fl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));  
        
        expander=new Button(this);
        expander.setLayoutParams(new LayoutParams(mainbuttonsize, mainbuttonsize));
        expander.setBackgroundResource(R.drawable.backarrow);
      
        expander.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
				if(isexpanded)
	        	{
	        		expander.setBackgroundResource(R.drawable.backarrow2);
	        	}
	        	else
	           expander.setBackgroundResource(R.drawable.arr2);
	        	
	        }
	        else
	        if(me.getAction() == MotionEvent.ACTION_MOVE)
	        {
	            
	        }
	        else 
	        if(me.getAction() == MotionEvent.ACTION_UP)
	        {
	        	sp1.play(expl1, 1, 1, 0, 0,1);
	        	if (isexpanded) 
	        	{
	        	    isexpanded = false;
	        	    expander.setBackgroundResource(R.drawable.arr);
	        	   l1.startAnimation(new CollapseAnimation(l1, 0,(int)(screenWidth*0.3), 0.1));
	        	}
	        	else 
	        		{
	        			isexpanded = true;
	        			expander.setBackgroundResource(R.drawable.backarrow);
	        			l1.startAnimation(new ExpandAnimation(l1, 0,layoutsize,0.1));
	        			
	        		}
	         }
				
				return true;
			}
		});
       
        
        save=new Button(this);
        save.setLayoutParams(p6);
        save.setId(2);
        
        iil2=new RelativeLayout(this);
        iil2.setLayoutParams(pt22);
        iil2.addView(save);
        iil2.setBackgroundColor(Color.argb(100,238, 224, 229));
        save.setBackgroundResource(R.drawable.save);
        save.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
    	           save.setBackgroundResource(R.drawable.save2);
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
    	        {
    	            
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_UP)
    	        {
    	        	sp1.play(expl1, 1, 1, 0, 0,1);
    	        	save.setBackgroundResource(R.drawable.save);
    	        	saveme();
    	        }	
				return true;
			}
		});
       
        deletepage =new Button(this);
        deletepage.setLayoutParams(p6);
        iil3=new RelativeLayout(this);
        iil3.setLayoutParams(pt33);
        iil3.addView(deletepage);
        iil3.setBackgroundColor(Color.argb(100,238, 224, 229));
        deletepage.setBackgroundResource(R.drawable.deletepage1);
        deletepage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
        final int ID_Rect     = 1;
    	final int ID_Cir   = 2;
    	final int ID_Oval = 3;
    	final int ID_Line   = 4;
    	final int ID_Free  = 5;	
    	final int ID_OK     = 6;
    	
    	ActionItem nextItem 	= new ActionItem(ID_Rect, "Rectangle", getResources().getDrawable(R.drawable.rectangle));
		ActionItem prevItem 	= new ActionItem(ID_Cir, "Circle", getResources().getDrawable(R.drawable.circle));
        ActionItem searchItem 	= new ActionItem(ID_Oval, "Oval", getResources().getDrawable(R.drawable.oval));
        ActionItem infoItem 	= new ActionItem(ID_Line, "Line", getResources().getDrawable(R.drawable.line));
        ActionItem eraseItem 	= new ActionItem(ID_Free, "Free Hand", getResources().getDrawable(R.drawable.draw));
       // ActionItem okItem 		= new ActionItem(ID_OK, "OK", getResources().getDrawable(R.drawable.menu_ok));
        final QuickAction quickAction = new QuickAction(this, QuickAction.VERTICAL);
        quickAction.addActionItem(nextItem);
		quickAction.addActionItem(prevItem);
        quickAction.addActionItem(searchItem);
        quickAction.addActionItem(infoItem);
        quickAction.addActionItem(eraseItem);
        //quickAction.addActionItem(okItem);
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {				
				ActionItem actionItem = quickAction.getActionItem(pos);
                 
				
				if (actionId == ID_Rect) {
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	ShapeObject_to_be_created = ShapeRect;
    	        	shapemenuclicked = true;
    	        	brushwidthmenuclicked = false;
				} else if (actionId == ID_Cir) {
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	ShapeObject_to_be_created = ShapeCircle;
    	        	
    	        	shapemenuclicked = true;
    	        	
    	        	brushwidthmenuclicked = false;
				} else if(actionId==ID_Oval){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	ShapeObject_to_be_created = ShapeOval;
    	        	
    	        	shapemenuclicked = true;
    	        	
    	        	brushwidthmenuclicked = false;
				}else if(actionId==ID_Line){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	ShapeObject_to_be_created = ShapeLine;
    	         	
    	         	shapemenuclicked = true;
    	         	
    	         	brushwidthmenuclicked = false;
				}else if(actionId==ID_Free){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	ShapeObject_to_be_created = ShapeFreehand;
    	        	
    	        	shapemenuclicked = true;
    	        	
    	        	brushwidthmenuclicked = false;
				}
			}
		});
		
		
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
			}
		});
        shapes=new Button(this);
        shapes.setLayoutParams(p1);
        shapes.setId(3);
        iil4=new RelativeLayout(this);
        iil4.setLayoutParams(pt11);
        iil4.addView(shapes);
        iil4.setBackgroundColor(Color.argb(100,238, 224, 229));
        shapes.setBackgroundResource(R.drawable.shapes);
        shapes.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				 if(me.getAction() == MotionEvent.ACTION_DOWN)
     	        {
     	        	
					 shapes.setBackgroundResource(R.drawable.shapes2);
     	        	}
     	        	
     	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
     	        {
     	            
     	        }
     	        else if(me.getAction() == MotionEvent.ACTION_UP)
     	        {
     	        	sp1.play(expl1, 1, 1, 0, 0,1);
     	        	
     	        	shapes.setBackgroundResource(R.drawable.shapes);
     	        	mode=0;
     	        	
     	        	quickAction.show(v);
     	        }
				return true;
			}
		});
        
        erase=new Button(this);
        erase.setLayoutParams(p2);
        erase.setId(4);
        iil5=new RelativeLayout(this);
        iil5.setLayoutParams(pt22);
        iil5.addView(erase);
        iil5.setBackgroundColor(Color.argb(100,238, 224, 229));
        erase.setBackgroundResource(R.drawable.refresh);
        erase.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
    	           erase.setBackgroundResource(R.drawable.refresh2);
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
    	        {
    	            
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_UP)
    	        {
    	        	
    	        	sp1.play(expl1, 1, 1, 0, 0,1);
    	        	erase.setBackgroundResource(R.drawable.refresh);
    	        	
    	        }
				return true;
			}
		});
       
        
        setting=new Button(this);
        setting.setLayoutParams(p1);
        setting.setId(5);
        iil6=new RelativeLayout(this);
        iil6.setLayoutParams(pt33);
        iil6.addView(setting);
        iil6.setBackgroundColor(Color.argb(100,238, 224, 229));
        setting.setBackgroundResource(R.drawable.blac);
        setting.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
    	           setting.setBackgroundResource(R.drawable.red);
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
    	        {
    	            
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_UP)
    	        {
    	        	sp1.play(expl1, 1, 1, 0, 0,1);
    	        	setting.setBackgroundResource(R.drawable.blac);
    	        	mode=1;
    	        	//currentGraphicObject.get
    	        	/*Path p1=new Path();
    	        	p1.addCircle(200,200, 100, Path.Direction.CW);
    	        	((Circle)currentGraphicObject).reset();
    	        	((Circle)currentGraphicObject).getPath().addPath(p1);*/
    	        	
    	            
    	        }
				return true;
			}
		});
        
        exit=new Button(this);
        exit.setLayoutParams(p2);
        exit.setId(6);
        iil9=new RelativeLayout(this);
        iil9.setLayoutParams(pt33);
        iil9.addView(exit);
        iil9.setBackgroundColor(Color.argb(100,238, 224, 229));
        
        exit.setBackgroundResource(R.drawable.exit2);
        exit.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
    	           exit.setBackgroundResource(R.drawable.exit1);
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
    	        {
    	            
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_UP)
    	        {
    	        	sp1.play(expl1, 1, 1, 0, 0,1);
    	        	exit.setBackgroundResource(R.drawable.exit2);
    	        	finish();
    	            
    	        }
				return true;
			}
		});
        
       //second drop down
        final int ID_Red     = 1;
    	final int ID_Green   = 2;
    	final int ID_Yellow = 3;
    	final int ID_Black   = 4;
    	final int ID_Blue  = 5;	
    	
    	
    	ActionItem nextItem2 	= new ActionItem(ID_Red, "Red", getResources().getDrawable(R.drawable.redb));
		ActionItem prevItem2 	= new ActionItem(ID_Green, "Green", getResources().getDrawable(R.drawable.green));
        ActionItem searchItem2 	= new ActionItem(ID_Yellow, "Yellow", getResources().getDrawable(R.drawable.yellow));
        ActionItem infoItem2 	= new ActionItem(ID_Black, "Black", getResources().getDrawable(R.drawable.black));
        ActionItem eraseItem2 	= new ActionItem(ID_Blue, "Blue", getResources().getDrawable(R.drawable.blue));
       // ActionItem okItem 		= new ActionItem(ID_OK, "OK", getResources().getDrawable(R.drawable.menu_ok));
        final QuickAction quickAction2 = new QuickAction(this, QuickAction.VERTICAL);
        quickAction2.addActionItem(nextItem2);
		quickAction2.addActionItem(prevItem2);
        quickAction2.addActionItem(searchItem2);
        quickAction2.addActionItem(infoItem2);
        quickAction2.addActionItem(eraseItem2);
        //quickAction.addActionItem(okItem);
        quickAction2.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {				
				ActionItem actionItem = quickAction2.getActionItem(pos);
                 
				//here we can filter which action item was clicked with pos or actionId parameter
				if (actionId == ID_Red) {
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	p.setBackgroundColor(Color.RED);
    	        	checkColor=1;
				} else if (actionId == ID_Green) {
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	p.setBackgroundColor(Color.GREEN);
    	        	checkColor=2;
				} else if(actionId==ID_Yellow){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	
    	        	p.setBackgroundColor(Color.YELLOW);
    	        	checkColor=3;
				}else if(actionId==ID_Black){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	checkColor=4;
    	        	p.setBackgroundColor(Color.BLACK);
				}else if(actionId==ID_Blue){
					sp1.play(expl1, 1, 1, 0, 0,1);
    	        	checkColor=5;
    	        	p.setBackgroundColor(Color.BLUE);
				}
			}
		});
		
		
		quickAction2.setOnDismissListener(new QuickAction.OnDismissListener() {			
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
			}
		});
        
        changeBcolor=new Button(this);
        changeBcolor.setLayoutParams(p6);
        changeBcolor.setId(9);
        changeBcolor.setBackgroundResource(R.drawable.background);
        iil7=new RelativeLayout(this);
        iil7.setLayoutParams(pt11);
        iil7.addView(changeBcolor);
        iil7.setBackgroundColor(Color.argb(100,238, 224, 229));
        changeBcolor.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction() == MotionEvent.ACTION_DOWN)
    	        {
    	        	 changeBcolor.setBackgroundResource(R.drawable.backgrounicon2);
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_MOVE)
    	        {
    	          
    	        }
    	        else if(me.getAction() == MotionEvent.ACTION_UP)
    	        {
    	        	sp1.play(expl1, 1, 1, 0, 0,1);
    	        	 changeBcolor.setBackgroundResource(R.drawable.background);
    	        	 quickAction2.show(v);
    	        	
    	        	 /*LaunchColorPicker();
    	         	colormenuclicked = true;
    	         	//erasemenuclicked = false;
    	         	brushwidthmenuclicked = false;*/
    	     }
				return true;
			}
		});
       
        
      
       
        
        changePwidth=new Button(this);
        changePwidth.setLayoutParams(p6);
        changePwidth.setId(11);
        changePwidth.setBackgroundResource(R.drawable.changewidth);
        iil8=new RelativeLayout(this);
        iil8.setLayoutParams(pt22);
        iil8.addView(changePwidth);
        iil8.setBackgroundColor(Color.argb(100,238, 224, 229));
        changePwidth.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
        
        
       
        //l1.setBackgroundColor(Color.rgb(238, 224, 229));
        
        File direct = new File(Environment.getExternalStorageDirectory() + "/androidpaint");

        if(!direct.exists())
         {
             if(direct.mkdir());//directory is created;

         }
        mImagePath = Environment.getExternalStorageDirectory() + "/androidpaint";
       
       
    }
    public void set1()
    {
    	//il1.addView(iil1);
    	il1.addView(iil2);
   	 	il1.addView(iil3);
   	 	il2.addView(iil4);
   	 	il2.addView(iil5);
   	 	il2.addView(iil6);
   	 	il3.addView(iil7);
   	 	il3.addView(iil8);
   	 	
   	 	il3.addView(iil9);
   	 	l1.addView(il1);
   	 	l1.addView(il2);
   	 	l1.addView(il3);
   	 	//il3.addView(iil8);
   	 	//il3.addView(iil9);
        fl.addView(l1);
       // fl.addView(l2);
      //  l1.startAnimation(new ExpandAnimation(l1, 0,(int)(screenWidth*0.3),0.1));
    	fl.addView(expander);
    }
   
   
   
   
    public void saveme()
    {
    	if(isFileAlreadySaved == false)
    	{
			  Calendar currentDate = Calendar.getInstance();
			  SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMMddHmmss");
			  String dateNow = formatter.format(currentDate.getTime());
			  if(StartingActivity.isToBeEdited == true)
			  {
				  savedFilePath = FileList.imageFilePath; 
			  }
			  else
			  {
				  savedFilePath = mImagePath + "/" + dateNow +".9.png";  
			  }
			  file = new File(savedFilePath);
			  FileOutputStream fos;
			  try
			  {
				  fos = new FileOutputStream(file);
				  bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
				  fos.close();
				  isFileAlreadySaved = true;
				  //isTempFileAlreadySaved = false;
				  //isTempFileToBeDeleted = true;
		            
			  } catch (FileNotFoundException e)
			  {
		       Log.e("Panel", "FileNotFoundException", e);
			  } 
			  catch (IOException e) 
			  {
		       Log.e("Panel", "IOEception", e);
			  }
    }
    }
    
    //test
    public void onPause(){
    	super.onPause();
    	p.surfaceDestroyed(p.getHolder());
    	
    }
    
    public void onStop(){
    	super.onStop();
    	p.surfaceDestroyed(p.getHolder());
		
    	
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	p.surfaceDestroyed(p.getHolder());
		
    		graphicobjects.clear();
			number_of_graphicObjects = 0;
    }
    
   //test
   public void onResume(){
	   super.onResume();
	  
		
   	Display display = getWindowManager().getDefaultDisplay();
   	Rect rectDisplay = new Rect();
		display.getRectSize(rectDisplay);
		int bitmapWidth = rectDisplay.width();
		int bitmapHeight = rectDisplay.height();
   	
   	if(StartingActivity.isToBeEdited == true){
   		
   		
   			BitmapFactory.Options opts = new BitmapFactory.Options();;
   	   		opts.inMutable = true;
   	   		
   	   		bitmap = BitmapFactory.decodeFile(FileList.imageFilePath, opts);
   		
   	}
   	else{
   		bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
   		
   	}
   	
	bitmapCanvas = new Canvas(bitmap);
	p = new Panel(this);
	ImageView imageView = new ImageView(this);
	imageView.setImageBitmap(bitmap);
	fl.addView(p);
	
	setContentView(fl);
	set1();
    }
    
	public void onStart(){
    	super.onStart();
    	
    
    }
	
	@Override
	public void onBackPressed()
	{
		if(isFileAlreadySaved == false)
		{	
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Do you want to Save the changes...");
			alertDialog.setMessage("Save the changes...");
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
			{
			   public void onClick(DialogInterface dialog, int which)
			   {
				   saveme();
				   Intent iStartingActivity = new Intent();
					iStartingActivity.setClassName("com.example.trial","com.example.trial.StartingActivity");
					startActivity(iStartingActivity);
			   }
			});
			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
			{
			public void onClick(DialogInterface dialog, int which)
			{
			     
				Intent iStartingActivity = new Intent();
				iStartingActivity.setClassName("com.example.trial","com.example.trial.StartingActivity");
				startActivity(iStartingActivity);
			 }
			});
			alertDialog.show();
		}
		
		else if (isFileAlreadySaved == true)
		{
			Intent iStartingActivity = new Intent();
			iStartingActivity.setClassName("com.example.trial","com.example.trial.StartingActivity");
			startActivity(iStartingActivity);
		}
	}
	
    public void onSaveInstanceState(Bundle savedInstanceState){
    	//savedInstanceState.p
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	
    }
    
    
    
    private void InitializePaint()
    {
    	mPaint.setDither(true);
	        
	    mPaint.setColor(Color.rgb(100, 100,100));

    	mPaint.setStyle(Paint.Style.STROKE);

    	mPaint.setStrokeJoin(Paint.Join.ROUND);

    	mPaint.setStrokeCap(Paint.Cap.ROUND);
    	
    	mPaint.setStrokeWidth(BrushWidth);	
	
	}
    
    	
    

private void LaunchColorPicker(){
	Intent launchcolorpicker = new Intent();
	launchcolorpicker.setClassName("com.example.trial", "com.example.trial.ColorPicker");
	launchcolorpicker.setAction("com.example.trial.android.intent.action.COLORPICKER");
	launchcolorpicker.addCategory("CATEGORY_DEFAULT");
	launchcolorpicker.setType("vnd.somitsolutions.color/vnd.somitsolutions.color-value");
	
	try {
    	startActivityForResult(launchcolorpicker,REQUEST_CODE);
    
    }
    catch(ActivityNotFoundException e){
    	Log.e("IntentExample", "Activity could not be started...");
    }   
}

public void onActivityResult(int requestcode, int resultcode, Intent result ) {
	
	if(requestcode == REQUEST_CODE){
    	if(resultcode == RESULT_OK){
    		color[0] = (result.getDoubleArrayExtra("com.example.trial.colorpicker.color_of_the_shape"))[0];
    		color[1] = (result.getDoubleArrayExtra("com.example.trial.colorpicker.color_of_the_shape"))[1];
    		color[2] = (result.getDoubleArrayExtra("com.example.trial.colorpicker.color_of_the_shape"))[2];
    		
    	}
	}
}


    class Panel extends SurfaceView implements SurfaceHolder.Callback {
        
    	public TutorialThread _thread;
    	
        public Panel(Context context) {
            super(context);
            getHolder().addCallback(this);
            _thread = new TutorialThread(getHolder(), this);
           setFocusable(true);
           setDrawingCacheEnabled(true);
        }
        
        
        public ArrayList<Shape> getGraphicObjects()
        {
        	return graphicobjects;
        }
 
        @Override
        public boolean onTouchEvent(MotionEvent event) {
        	
        	isFileAlreadySaved = false;
	        	synchronized (_thread.getSurfaceHolder()) {
	        		
	        		if(event.getAction() == MotionEvent.ACTION_DOWN){
	        			thisarea=new postext();
        				thisarea.p1=new Point(event.getX(),event.getY());
	        			for(postext check:coveredarea)
	        			{
	        				
	        				
	        			if(!check.checkarea( thisarea.p1))
	        			{
	        				checkcontain=check;
	        				
	        				flag=true;
	        				
	        			}
	        			}
	        			if(!flag)
	        			{
	        			
	        			if(shapemenuclicked == true){
		        			
		        			if(ShapeObject_to_be_created == ShapeLine)
		        			{
		        				currentGraphicObject = new Line();
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeFreehand)
		        			{	
		        				currentGraphicObject = new FreeHand();
		        				((FreeHand)currentGraphicObject).getPath().moveTo(event.getX(), event.getY());
		
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeRect)
		        			{	
		        				
		        				currentGraphicObject = new Rectangle();
		        			}
		        			if(ShapeObject_to_be_created == ShapeCircle)
		        			{	
		        				currentGraphicObject = new Circle();
		        			}
		        			if(ShapeObject_to_be_created == ShapeOval)
		        			{
		        				currentGraphicObject = new Oval();
		        			}
	        			}
	        			}
	        			else
	        			{
	        			flag=false;
	        			wasClickedInside=true;
	        			preDisplacementX=0;
	        			preDisplacementY=0;
	        			}
	        			
	        		
	        			
	        		}
	        		
	       
	        		else if(event.getAction() == MotionEvent.ACTION_MOVE){
	        			
	        			
	        		/*	if(shapemenuclicked == true ){
		        			
		        			
		        			if(ShapeObject_to_be_created == ShapeFreehand){
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        			}
		        			
		        			
		        			if(ShapeObject_to_be_created == ShapeLine){
		        				
		        			}
		        			
		        			
		        			if(ShapeObject_to_be_created == ShapeRect){
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeCircle){
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeOval){
		        				
		        			}
	        			}*/
	        			if(wasClickedInside)
	        			{
	        			displacementX=(int) (event.getX()-thisarea.p1.getX());
    					displacementY=(int) (event.getY()-thisarea.p1.getY());
    					preDisplacementX=displacementX;
    					preDisplacementY=displacementY;
    					if(checkcontain.p2.getX()!=0)
    					{
    						
    						int  left=(int) (checkcontain.left+(checkcontain.right-checkcontain.left)/2+displacementX);
    						int  top=(int) (checkcontain.top+(checkcontain.bottom-checkcontain.top)/2+displacementY);
    						checkcontain.thistext=t1;
    						//checkcontain.thistext=new TextView(MainActivity.this);
    						checkcontain.thistext.setPadding(left, top, 0, 0);
    					}
    					switch (checkcontain.obj) 
    					{
						case 0:
						{
							/*checkcontain.graphicInThis.reset();
							Toast.makeText(getApplicationContext(), "this is a circle", 4000).show();
							checkcontain.graphicInThis.getPath().moveTo(checkcontain.p1.getX()+displacementX, checkcontain.p1.getY()+displacementY);
							checkcontain.graphicInThis.getPath().lineTo(checkcontain.p2.getX()+displacementX, checkcontain.p2.getY()+displacementY);
							*/
							break;
						}
						case 1:
						{
							float radius = ((Circle)checkcontain.graphicInThis).getRadius();
    						checkcontain.graphicInThis.reset();
    						Path p1=new Path();
							p1.addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX, (checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY, radius,Path.Direction.CW);
							checkcontain.graphicInThis.getPath().addPath(p1);
							((Circle)checkcontain.graphicInThis).getPath().addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX,(checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY+radius+10, 10,Path.Direction.CW);
							((Circle)checkcontain.graphicInThis).getPath().addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX, (checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY-radius-10, 10,Path.Direction.CW);
							
							break;
						}
						case 2:
						{
							checkcontain.graphicInThis.reset();
							
							((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().setX(checkcontain.p1.getX()+displacementX);
	        				((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().setY(checkcontain.p1.getY()+displacementY);
	        				float tempX = ((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().getX();
    						float tempY = ((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().getY();
    						float tempX1 = checkcontain.p2.getX()+displacementX;
    						float tempY1 = checkcontain.p2.getY()+displacementY;
    						if(tempX<=tempX1 && tempY>=tempY1)
    						{
    							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX,tempY1,tempX1,tempY);	
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
    						}
        				
    						if(tempX<=tempX1 && tempY<=tempY1)
    						{
        					
    							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX,tempY,tempX1,tempY1);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
        					
    						}
        				
    						if(tempX>=tempX1  && tempY>=tempY1)
    						{
        					
    							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX1,tempY1,tempX,tempY);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
        					
    						}
    						
    						if(tempX>=tempX1 && tempY<=tempY1)
    						{
    							
    							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX1,tempY,tempX,tempY1);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
    							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
    						}
    						Path p1=new Path();
    						p1.addOval(((Oval)checkcontain.graphicInThis).getRectangle(), Path.Direction.CW);
    						
    						((Oval)checkcontain.graphicInThis).getPath().addPath(p1);
							break;
						}
						case 3:
						{
							Point temp = new Point(checkcontain.p1.getX()+displacementX,checkcontain.p1.getY()+displacementY);
							((Rectangle) checkcontain.graphicInThis).settemppointOfOneEndRectangle(temp);
							float tempX = ((Rectangle) checkcontain.graphicInThis).gettemppointOfOneEndRectangle().getX();
	        				
    						float tempY = ((Rectangle) checkcontain.graphicInThis).gettemppointOfOneEndRectangle().getY();
    						float tempX1 = checkcontain.p2.getX()+displacementX;
    						float tempY1 = checkcontain.p2.getY()+displacementY;
    						
    						checkcontain.graphicInThis.reset();
    						Path p1 =new Path();
    						if(tempX<tempX1 && tempY>tempY1)
    						{
    							p1.addRect(tempX, tempY1, tempX1, tempY, Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
    						}
    						if(tempX<tempX1 && tempY<tempY1)
    						{
    							p1.addRect(tempX, tempY, tempX1, tempY1, Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
    						}
    						if(tempX>tempX1  && tempY>tempY1)
    						{
    							p1.addRect(tempX1, tempY1, tempX, tempY, Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
    						}
    						if(tempX>tempX1 && tempY<tempY1)
    						{
    							p1.addRect(tempX1, tempY, tempX, tempY1, Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
    							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
    						}
							break;
						}
    					}
    					
    					switch(checkColor)
        				{
        				case 1:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.RED);
        					break;
        				case 2:
        					p.setBackgroundColor(Color.YELLOW);
        					p.setBackgroundColor(Color.GREEN);
        					break;
        				case 3:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.YELLOW);
        					break;
        				case 4:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.BLACK);
        					break;
        				case 5:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.BLUE);
        					break;
        				}
	        		}
	        		}
	        		
	        		
	        		else if(event.getAction() == MotionEvent.ACTION_UP){
	        		
        				thisarea.p2=new Point(event.getX(),event.getY());
        				int x=0;
        				if(!wasClickedInside)
        				{
	        			for(postext check:coveredarea)
	        			{
	        				
	        				
	        			if(!check.checkarea( thisarea.p2))
	        			{
	        				checkcontain=check;
	        				
	        				flag=true;
	        				
	        			}
	        			}
	        			if(flag==false)
	        			{
	        				
	        				if(shapemenuclicked == true )
	        				{
	        			    
		        			
	        					if(ShapeObject_to_be_created == ShapeLine)
	        					{	
	        						((Line) currentGraphicObject).getBegin().setX(thisarea.p1.getX());
			        				
			        				((Line) currentGraphicObject).getBegin().setY(thisarea.p1.getY());
	        						((Line) currentGraphicObject).getEnd().setX(event.getX());
	        						((Line) currentGraphicObject).getEnd().setY(event.getY());
		            			
	        						Point temp_begin = ((Line)currentGraphicObject).getBegin();
	        						Point temp_end = ((Line)currentGraphicObject).getEnd();
	        						((Line) currentGraphicObject).setBegin(temp_begin);
		            			
	        						((Line)currentGraphicObject).getPath().moveTo(temp_begin.getX(), temp_begin.getY());
	        						((Line)currentGraphicObject).getPath().lineTo(temp_end.getX(), temp_end.getY());
	        						coveredarea.add(thisarea);
	        						thisarea.graphicInThis=currentGraphicObject;
	        						thisarea.obj=0;//0-line,1-circle,2-oval,3-rectangle 4 free hand
	        						//thiszre
		            			
	        					}
		        			
		        			
	        					if(ShapeObject_to_be_created == ShapeFreehand)
	        					{
	        						((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
	        						
	        						((FreeHand)currentGraphicObject).getGraphicsPath().add(((FreeHand)currentGraphicObject).getPath());
	        						//thisarea.graphicInThis=currentGraphicObject;
	        						//thisarea.obj=4;//0-line,1-circle,2-oval,3-rectangle 4 free hand
		        				
	        					}
	        					if(ShapeObject_to_be_created == ShapeRect)
	        					{
	        						Point temp = new Point(thisarea.p1.getX(),thisarea.p1.getY());
			        				((Rectangle) currentGraphicObject).settemppointOfOneEndRectangle(temp);
	        						float tempX = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getX();
		        				
	        						float tempY = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getY();
	        						float tempX1 = event.getX();
	        						float tempY1 = event.getY();
	        						if(tempX<tempX1 && tempY>tempY1)
	        						{
	        							((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY1, tempX1, tempY, Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
	        						}
	        						if(tempX<tempX1 && tempY<tempY1)
	        						{
	        							((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY, tempX1, tempY1, Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
	        						}
	        						if(tempX>tempX1  && tempY>tempY1)
	        						{
	        							((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY1, tempX, tempY, Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
	        						}
	        						if(tempX>tempX1 && tempY<tempY1)
	        						{
	        							((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY, tempX, tempY1, Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
	        							((Rectangle)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
	        						}
	        						
	        						thisarea.graphicInThis=currentGraphicObject;
	        						thisarea.obj=3;//0-line,1-circle,2-oval,3-rectangle 4 free hand
		        				
	        					}
	        					if(ShapeObject_to_be_created == ShapeCircle)
	        					{
	        						((Circle)currentGraphicObject).getOneEndOfTheCircle().setX(thisarea.p1.getX());
			        				((Circle)currentGraphicObject).getOneEndOfTheCircle().setY(thisarea.p1.getY());
			        				
	        						float tempX1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getX();
	        						float tempY1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getY();
	        						float tempX2 = event.getX();
	        						float tempY2 = event.getY();
	        						double temp = Math.pow((tempX1-tempX2),2) + Math.pow((tempY1-tempY2),2);
	        						float radius = (float)Math.sqrt(temp)/2;
	        						((Circle)currentGraphicObject).getPath().addCircle((tempX1 + tempX2)/2,(tempY1 + tempY2)/2, radius, Path.Direction.CW);
	        						((Circle)currentGraphicObject).setReadius(radius);
	        						if(tempY1>tempY2)
	        						{
	        							((Circle)currentGraphicObject).getPath().addCircle((tempX1+tempX2)/2, (tempY1+tempY2)/2+radius+10, 10,Path.Direction.CW);
	        							((Circle)currentGraphicObject).getPath().addCircle((tempX1+tempX2)/2, (tempY2+tempY1)/2-radius-10, 10,Path.Direction.CW);
	        						}
	        						else
	        						{
	        							((Circle)currentGraphicObject).getPath().addCircle((tempX1+tempX2)/2, (tempY2+tempY1)/2-radius-10, 10,Path.Direction.CW);
	        							((Circle)currentGraphicObject).getPath().addCircle((tempX1+tempX2)/2, (tempY1+tempY2)/2+radius+10, 10,Path.Direction.CW);
	        						}
	        						thisarea.graphicInThis=currentGraphicObject;
	        						thisarea.obj=1;//0-line,1-circle,2-oval,3-rectangle 4 free hand
	        					}
	        					if(ShapeObject_to_be_created == ShapeOval)
	        					{	
	        						((Oval)currentGraphicObject).getoneEndOfTheOval().setX(thisarea.p1.getX());
			        				((Oval)currentGraphicObject).getoneEndOfTheOval().setY(thisarea.p1.getY());
	        						float tempX = ((Oval)currentGraphicObject).getoneEndOfTheOval().getX();
	        						float tempY = ((Oval)currentGraphicObject).getoneEndOfTheOval().getY();
	        						float tempX1 = event.getX();
	        						float tempY1 = event.getY();
		        				
	        						if(tempX<=tempX1 && tempY>=tempY1)
	        						{
	        							((Oval)currentGraphicObject).getRectangle().set(tempX,tempY1,tempX1,tempY);	
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
	        						}
		        				
	        						if(tempX<=tempX1 && tempY<=tempY1)
	        						{
		        					
	        							((Oval)currentGraphicObject).getRectangle().set(tempX,tempY,tempX1,tempY1);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
		        					
	        						}
		        				
	        						if(tempX>=tempX1  && tempY>=tempY1)
	        						{
		        					
	        							((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY1,tempX,tempY);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
		        					
	        						}
	        						
	        						if(tempX>=tempX1 && tempY<=tempY1)
	        						{
	        							
	        							((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY,tempX,tempY1);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
	        							((Oval)currentGraphicObject).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
	        						}
	        						
	        						((Oval)currentGraphicObject).getPath().addOval(((Oval)currentGraphicObject).getRectangle(), Path.Direction.CW);
		        				
	        						thisarea.graphicInThis=currentGraphicObject;
	        						thisarea.obj=2;//0-line,1-circle,2-oval,3-rectangle 4 free hand
	        					}
	        					 coveredarea.add(thisarea);
	        				}
	        				switch(checkColor)
	        				{
	        				case 1:
	        					p.setBackgroundColor(Color.GREEN);
	        					p.setBackgroundColor(Color.RED);
	        					break;
	        				case 2:
	        					p.setBackgroundColor(Color.YELLOW);
	        					p.setBackgroundColor(Color.GREEN);
	        					break;
	        				case 3:
	        					p.setBackgroundColor(Color.GREEN);
	        					p.setBackgroundColor(Color.YELLOW);
	        					break;
	        				case 4:
	        					p.setBackgroundColor(Color.GREEN);
	        					p.setBackgroundColor(Color.BLACK);
	        					break;
	        				case 5:
	        					p.setBackgroundColor(Color.GREEN);
	        					p.setBackgroundColor(Color.BLUE);
	        					break;
	        				}
	        			}
	        			
	        			else
	        			{
	        				flag=false;
	        				
	        				final View layout = View.inflate(MainActivity.this, R.layout.mydialog, null);

	        			    final EditText savedText = ((EditText) layout.findViewById(R.id.myEditText));
	        				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
	        				alertDialog.setTitle("Enter the name of entity");
	        				
	        				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        				{
	        				   public void onClick(DialogInterface dialog, int which)
	        				   {
	        					   String myTextString = savedText.getText().toString().trim();
	        					  // t1= new TextView(MainActivity.this);
	        					   t1.setText(myTextString);
	        					   Boolean flag2=false;
	        					   for(int i=0;i<fl.getChildCount();i++)
	        						   if(checkcontain.thistext==fl.getChildAt(i))
	        							   flag2=true;
	        					   if(flag)
	        					   {
	        						   checkcontain.thistext.setText(myTextString);
	        					   }
	        					   else
	        						   
	        					   checkcontain.thistext=t1;
	        					   
	        					   fl.addView(checkcontain.thistext);
	        					   int  left=(int) (checkcontain.left+(checkcontain.right-checkcontain.left)/2);
	        					   int  top=(int) (checkcontain.top+(checkcontain.bottom-checkcontain.top)/2);
	        					   checkcontain.thistext.setPadding(left, top, 0, 0);
	        				   }
	        				});
	        				alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
	        				{
	        				public void onClick(DialogInterface dialog, int which)
	        				{
	        				     
	        					
	        				 }
	        				});
	        				alertDialog.setView(layout);
	        				alertDialog.show();
	        				
	        				
	        				
	        			}
	        			
	        			
	        			if(colormenuclicked == false && shapemenuclicked == true){
		        			currentGraphicObject.setrgb(100,100,100);	
		        		}
		        		if(colormenuclicked == true && shapemenuclicked == true){
		        			currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        		}
		        		
		        		if(brushwidthmenuclicked == true){
		        			currentGraphicObject.setStrokeWidth(BrushWidth);
		        		}
		        		graphicobjects.add(currentGraphicObject);
		        		number_of_graphicObjects++;
		        		
	        		}
	        		//else of wasclicked inside
        				else
        				{//0-line,1-circle,2-oval,3-rectangle 4 free hand
        					wasClickedInside=false;
        					displacementX=(int) (thisarea.p2.getX()-thisarea.p1.getX());
        					displacementY=(int) (thisarea.p2.getY()-thisarea.p1.getY());
        					checkcontain.left+=displacementX;
        					checkcontain.top+=displacementY;
        					//int  left=(int) (checkcontain.left+(checkcontain.right-checkcontain.left)/2);
     					  // int  top=(int) (checkcontain.top+(checkcontain.bottom-checkcontain.top)/2);
     					  // checkcontain.thistext.setPadding(left, top, 0, 0);
        					
        					switch (checkcontain.obj) 
        					{
							case 0:
							{
								checkcontain.graphicInThis.reset();
								Toast.makeText(getApplicationContext(), "this is a circle", 4000).show();
								checkcontain.graphicInThis.getPath().moveTo(checkcontain.p1.getX()+displacementX, checkcontain.p1.getY()+displacementY);
								checkcontain.graphicInThis.getPath().lineTo(checkcontain.p2.getX()+displacementX, checkcontain.p2.getY()+displacementY);
								
								
								checkcontain.p1.setX(checkcontain.p1.getX()+displacementX);
								checkcontain.p1.setY(checkcontain.p1.getY()+displacementY);
								checkcontain.p2.setX(checkcontain.p2.getX()+displacementX);
								checkcontain.p2.setY(checkcontain.p2.getY()+displacementY);
								break;
							}
							case 1:
							{
								//Toast.makeText(getApplicationContext(), "this is a circle", 4000).show();
								float tempX1 = checkcontain.p1.getX();
        						float tempY1 = checkcontain.p1.getY();
        						float tempX2 = checkcontain.p2.getX();
        						float tempY2 = checkcontain.p2.getY();
        						double temp = Math.pow((tempX1-tempX2),2) + Math.pow((tempY1-tempY2),2);
        						float radius = ((Circle)checkcontain.graphicInThis).getRadius();
        						
								checkcontain.graphicInThis.reset();
								//Toast.makeText(getApplicationContext(), "this is a circle", 4000).show();
								Path p1=new Path();
								
								p1.addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX, (checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY, radius,Path.Direction.CW);
								checkcontain.graphicInThis.getPath().addPath(p1);
								((Circle)checkcontain.graphicInThis).getPath().addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX,(checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY+radius+10, 10,Path.Direction.CW);
								((Circle)checkcontain.graphicInThis).getPath().addCircle((checkcontain.p1.getX()+checkcontain.p2.getX())/2+displacementX, (checkcontain.p1.getY()+checkcontain.p2.getY())/2+displacementY-radius-10, 10,Path.Direction.CW);
								checkcontain.p1.setX(checkcontain.p1.getX()+displacementX);
								checkcontain.p1.setY(checkcontain.p1.getY()+displacementY);
								checkcontain.p2.setX(checkcontain.p2.getX()+displacementX);
								checkcontain.p2.setY(checkcontain.p2.getY()+displacementY);
								break;
							}
							case 2:
							{
								
								checkcontain.graphicInThis.reset();
								
								((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().setX(checkcontain.p1.getX()+displacementX);
		        				((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().setY(checkcontain.p1.getY()+displacementY);
		        				float tempX = ((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().getX();
        						float tempY = ((Oval)checkcontain.graphicInThis).getoneEndOfTheOval().getY();
        						float tempX1 = checkcontain.p2.getX()+displacementX;
        						float tempY1 = checkcontain.p2.getY()+displacementY;
        						if(tempX<=tempX1 && tempY>=tempY1)
        						{
        							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX,tempY1,tempX1,tempY);	
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
        						}
            				
        						if(tempX<=tempX1 && tempY<=tempY1)
        						{
            					
        							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX,tempY,tempX1,tempY1);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
            					
        						}
            				
        						if(tempX>=tempX1  && tempY>=tempY1)
        						{
            					
        							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX1,tempY1,tempX,tempY);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
            					
        						}
        						
        						if(tempX>=tempX1 && tempY<=tempY1)
        						{
        							
        							((Oval)checkcontain.graphicInThis).getRectangle().set(tempX1,tempY,tempX,tempY1);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
        							((Oval)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
        						}
        						Path p1=new Path();
        						p1.addOval(((Oval)checkcontain.graphicInThis).getRectangle(), Path.Direction.CW);
        						((Oval)checkcontain.graphicInThis).getPath().addPath(p1);
        						checkcontain.p1.setX(checkcontain.p1.getX()+displacementX);
								checkcontain.p1.setY(checkcontain.p1.getY()+displacementY);
								checkcontain.p2.setX(checkcontain.p2.getX()+displacementX);
								checkcontain.p2.setY(checkcontain.p2.getY()+displacementY);
								break;
							}
							case 3:
							{
								
								Point temp = new Point(checkcontain.p1.getX()+displacementX,checkcontain.p1.getY()+displacementY);
								((Rectangle) checkcontain.graphicInThis).settemppointOfOneEndRectangle(temp);
								float tempX = ((Rectangle) checkcontain.graphicInThis).gettemppointOfOneEndRectangle().getX();
		        				
        						float tempY = ((Rectangle) checkcontain.graphicInThis).gettemppointOfOneEndRectangle().getY();
        						float tempX1 = checkcontain.p2.getX()+displacementX;
        						float tempY1 = checkcontain.p2.getY()+displacementY;
        						
        						checkcontain.graphicInThis.reset();
        						Path p1 =new Path();
        						if(tempX<tempX1 && tempY>tempY1)
        						{
        							p1.addRect(tempX, tempY1, tempX1, tempY, Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
        						}
        						if(tempX<tempX1 && tempY<tempY1)
        						{
        							p1.addRect(tempX, tempY, tempX1, tempY1, Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
        						}
        						if(tempX>tempX1  && tempY>tempY1)
        						{
        							p1.addRect(tempX1, tempY1, tempX, tempY, Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1-10, 10,Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY+10, 10,Path.Direction.CW);
        						}
        						if(tempX>tempX1 && tempY<tempY1)
        						{
        							p1.addRect(tempX1, tempY, tempX, tempY1, Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addPath(p1);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY-10, 10,Path.Direction.CW);
        							((Rectangle)checkcontain.graphicInThis).getPath().addCircle((tempX+tempX1)/2, tempY1+10, 10,Path.Direction.CW);
        						}
        						checkcontain.p1.setX(checkcontain.p1.getX()+displacementX);
								checkcontain.p1.setY(checkcontain.p1.getY()+displacementY);
								checkcontain.p2.setX(checkcontain.p2.getX()+displacementX);
								checkcontain.p2.setY(checkcontain.p2.getY()+displacementY);
								break;
							}

							
							}
        					
        				}
        				switch(checkColor)
        				{
        				case 1:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.RED);
        					break;
        				case 2:
        					p.setBackgroundColor(Color.YELLOW);
        					p.setBackgroundColor(Color.GREEN);
        					break;
        				case 3:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.YELLOW);
        					break;
        				case 4:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.BLACK);
        					break;
        				case 5:
        					p.setBackgroundColor(Color.GREEN);
        					p.setBackgroundColor(Color.BLUE);
        					break;
        				}
	        		}
	        		
	        	}
        	
        	
	      return true;
    
        }
 
        @Override
        public void onDraw(Canvas canvas) {
        	
	        	if(StartingActivity.isToBeEdited == true ){
	        		canvas.drawBitmap(bitmap, 0, 0, mPaint);
	        		bitmapCanvas.drawBitmap(bitmap, 0, 0, mPaint);
	        		
	        	}
        		for(int i = 0; i<number_of_graphicObjects; i++){
	        		
	        		Shape currentGraphicObject = graphicobjects.get(i);
	        		
	        			mPaint.setColor(Color.rgb(currentGraphicObject.getrgb()[0], currentGraphicObject.getrgb()[1], currentGraphicObject.getrgb()[2]));
	        			mPaint.setStrokeWidth(currentGraphicObject.getStrokeWidth());
	        		
	        		
	        		if(currentGraphicObject instanceof FreeHand){
	        			for (Path path : ((FreeHand)currentGraphicObject).getGraphicsPath()) {
	                	    canvas.drawPath(path, mPaint);
	                	    bitmapCanvas.drawPath(path,mPaint);
	                	    currentGraphicObject = null;
	            		}
	        		}
	        		
	        		else{
	        			canvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			bitmapCanvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			currentGraphicObject = null;
	        		}
	        	}
	      }
 
       
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        {
            
        	
        }
 
        
        public void surfaceCreated(SurfaceHolder holder) {
            _thread.setRunning(true);
            _thread.start();
        }
 
        
        public void surfaceDestroyed(SurfaceHolder holder) {
           
            boolean retry = true;
            _thread.setRunning(false);
            while (retry) {
                try {
                    _thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    
                }
            }
        }
    }
 
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
        private boolean _run = false;
 
        public TutorialThread(SurfaceHolder surfaceHolder, Panel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }
 
        public void setRunning(boolean run) {
            _run = run;
        }
 
        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }
 
		@SuppressLint("WrongCall")
		@Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas();
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                    	
                    }
                } finally {
                   
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
  }
