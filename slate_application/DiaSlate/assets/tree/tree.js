
var xa,ya;
var x,y,t=0,m;

function end()
{
clearInterval(intervalId);

}

//---------------------------------------------

window.onload = function(){
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");
	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText("Tap on image to view details", 105,canvas.height-10);
	
    db_image= new Image();
    db_image.src = "db.jpg";
    db_image.onload = function(){
    ctx.drawImage(db_image, canvas.width/2-20,3);
	}
	
    intervalId = setInterval(function () {
t++;
  draw1();
}, 30);
window.addEventListener('mousemove', mouseMove,true);
 

};

//---------------------------------------------

jQuery(document).ready(function(){
})

//---------------------------------------------

function draw1() {
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText(".", canvas.width/2,50+t);
	if(t==30)
	{
	end();
	 ms_image= new Image();
    ms_image.src = "parag.png";
    ms_image.onload = function(){
    ctx.drawImage(ms_image, canvas.width/2-20,80);
	} 
    t=0;	
	intervalId = setInterval(function () {
   t++;
  draw2();
}, 30);
}		
}

//---------------------------------------------------------------
function draw2() {
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText(".", canvas.width/2+t,130+t);
	ctx.fillText(".", canvas.width/2-t,130+t);
	if(t==20)
	{
	end();
	ys_image= new Image();
    ys_image.src = "ninad.png";
    ys_image.onload = function(){
    ctx.drawImage(ys_image, canvas.width/2+20,150);
	}
	ys1_image= new Image();
    ys1_image.src = "arun.png";
    ys1_image.onload = function(){
    ctx.drawImage(ys1_image, canvas.width/2-65,150);
	}
	 t=0;	
	 //to draw straight line
	    ctx.beginPath();
		ctx.moveTo(canvas.width/2-45, 220);
		ctx.lineTo(canvas.width/2-45, 200);
		ctx.lineTo(canvas.width/2-45, 220);
		ctx.lineTo(canvas.width/2+45, 220);
		ctx.lineTo(canvas.width/2+45, 200);
		ctx.lineTo(canvas.width/2+45, 220);
		ctx.closePath();
		ctx.stroke();
	intervalId = setInterval(function () {
    t++;
    draw3();
}, 30);
}	
}

//---------------------------------------------

function draw3() {
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText(".", canvas.width/2,220+t);
	if(t==20)
	{
	end();
    t=0;	
	intervalId = setInterval(function () {
   t++;
  draw4();
}, 30);
}		
}

//---------------------------------------------------------------

function draw4() {
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText(".", canvas.width/2-4*t,240);
	ctx.fillText(".", canvas.width/2+4*t,240);
	if(t==90)
	{
	end();
	
    t=0;	
	intervalId = setInterval(function () {
   t++;
  draw5();
}, 30);
}		
}

//---------------------------------------------------------------

function draw5() {
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");	
	ctx.fillStyle="black";
	ctx.font="bold italic 18px Comic Sans MS";
	ctx.fillText(".", canvas.width/2-350,240+t);
	ctx.fillText(".", canvas.width/2-250,240+t);
	ctx.fillText(".", canvas.width/2-150,240+t);
	ctx.fillText(".", canvas.width/2-50,240+t);
	ctx.fillText(".", canvas.width/2+50,240+t);
	ctx.fillText(".", canvas.width/2+150,240+t);
	ctx.fillText(".", canvas.width/2+250,240+t);
	ctx.fillText(".", canvas.width/2+350,240+t);
	if(t==20)
	{
	end();
	n_image= new Image();
    n_image.src = "aman.png";
    n_image.onload = function(){
    ctx.drawImage(n_image, canvas.width/2-370,260);
    }
    m_image= new Image();
    m_image.src = "nishant.png";
    m_image.onload = function(){
    ctx.drawImage(m_image, canvas.width/2-270,260);
    }	
    u_image= new Image();
    u_image.src = "vanya.png";
    u_image.onload = function(){
    ctx.drawImage(u_image, canvas.width/2-170,260);
	}
	r1_image= new Image();
    r1_image.src = "amit.png";
    r1_image.onload = function(){
    ctx.drawImage(r1_image, canvas.width/2-70,260);
	}
	
	r2_image= new Image();
    r2_image.src = "shekhar.png";
    r2_image.onload = function(){
    ctx.drawImage(r2_image, canvas.width/2+30,260);
	}
	
	r3_image= new Image();
    r3_image.src = "naveen.png";
    r3_image.onload = function(){
    ctx.drawImage(r3_image, canvas.width/2+130,260);
	}
	r4_image= new Image();
    r4_image.src = "vikram.jpg";
    r4_image.onload = function(){
    ctx.drawImage(r4_image, canvas.width/2+230,260);
	}
	r5_image= new Image();
    r5_image.src = "vinod.jpg";
    r5_image.onload = function(){
    ctx.drawImage(r5_image, canvas.width/2+330,260);
	}
}		
}


function mouseMove(e)
{
	var canvas = document.getElementById("workspace");
	var ctx = canvas.getContext("2d");
	var xPos=e.clientX;
	var yPos=e.clientY;
	if((xPos >canvas.width/2-370&& yPos>3 )&&( xPos<canvas.width/2-100 && yPos<53)){
    first();	
	}
	if((xPos >canvas.width/2-370&& yPos>110 )&&( xPos<canvas.width/2-100 && yPos<120)){
    second();	
	}
	if((xPos >canvas.width/2-270&& yPos>180 )&&( xPos<canvas.width/2 && yPos<190)){
    third();	
	}
	if((xPos >canvas.width/2-470&& yPos>200)&&( xPos<canvas.width/2 && yPos<210)){
    fourth();	
	}
	if((xPos >canvas.width/2-500&& yPos>260  )&&( xPos<canvas.width/2-400 && yPos<290)){
   fifth();	
	}
	if((xPos >canvas.width/2-440&& yPos>290  )&&( xPos<canvas.width/2-300 && yPos<310)){
    sixth();	
	}
	if((xPos >canvas.width/2-270&& yPos>260  )&&( xPos<canvas.width/2-220 && yPos<280)){
    seventh();	
	}
if((xPos >canvas.width/2-170&& yPos>260  )&&( xPos<canvas.width/2-100 && yPos<310)){
    eight();	
	}
if((xPos >canvas.width/2&& yPos>260  )&&( xPos<canvas.width/2+50 && yPos<310)){
    ninth();	
	}
if((xPos >canvas.width/2+80&& yPos>260  )&&( xPos<canvas.width/2+120 && yPos<310)){
    tenth();	
	}
if((xPos >canvas.width/2+130&& yPos>260  )&&( xPos<canvas.width/2+170 && yPos<310)){
    eleventh();	
	}
if((xPos >canvas.width/2+200&& yPos>260  )&&( xPos<canvas.width/2+250 && yPos<310)){
    twelth();	
	}
	}

function first()
{
var img = document.getElementById("image");
img.src="db1.png";
var element=document.getElementById("details");
element.innerHTML="Dr. Deepak.B.Phatak<br>Subrao M. Nilekani Chair Professor,<br>Department of CSE,IIT Bombay";
return false;
}

function second()
{
var img = document.getElementById("image");
img.src="parag.png";
var element=document.getElementById("details");
element.innerHTML="Mr.Parag Tiwari<br> Software Engineer,IIT Bombay <br> Project Guide";
return false;
}

function third()
{
var img = document.getElementById("image");
img.src="ninad1.png";
var element=document.getElementById("details");
element.innerHTML="Mr.Ninad Chilap<br> Software Engineer,IIT Bombay <br> Project Guide";
return false;
}

function fourth()
{
var img = document.getElementById("image");
img.src="arun1.png";
var element=document.getElementById("details");
element.innerHTML="Mr. Arun Nair<br> Research Assistant,IIT Bombay <br> Project Guide";
return false;
}

function fifth()
{
var img = document.getElementById("image");
img.src="aman1.png";
var element=document.getElementById("details");
element.innerHTML="Amandeep<br>Amity University<br>";
return false;
}

function sixth()
{
var img = document.getElementById("image");
img.src="nishant1.png";
var element=document.getElementById("details");
element.innerHTML="Nishant Kumar<br>CUSAT";
return false;
}

function seventh()
{
var img = document.getElementById("image");
img.src="vanya1.png";
var element=document.getElementById("details");
element.innerHTML="Vanya Kandlakunta<br>GITAM, Vishakhapatnam<br>";
return false;
}
function eight()
{
var img = document.getElementById("image");
img.src="amit1.png";
var element=document.getElementById("details");
element.innerHTML="Amit Kumar<br>NIT,Agartala<br>";
return false;
}
function ninth()
{
var img = document.getElementById("image");
img.src="shekhar1.png";
var element=document.getElementById("details");
element.innerHTML="Chandrashekhar Bobade<br>Revera Institute of Technology, Mumbai<br>";
return false;
}
function tenth()
{
var img = document.getElementById("image");
img.src="naveen1.png";
var element=document.getElementById("details");
element.innerHTML="Naveen Kumar<br>Indian School of Mines, Dhanbad<br>";
return false;
}
function eleventh()
{
var img = document.getElementById("image");
img.src="vikram1.jpg";
var element=document.getElementById("details");
element.innerHTML="Vikram Kore<br>RGU-IIIT, Basar<br>";
return false;
}
function twelth()
{
var img = document.getElementById("image");
img.src="vinod1.jpg";
var element=document.getElementById("details");
element.innerHTML="Parlapalli Vinod<br>RGU-IIIT RK Valley<br>";
return false;
}
