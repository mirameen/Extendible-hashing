import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet{
	PFont f,g;
	Directory dir=new Directory();
	int num=0,ind,bn,fl=0,bucketfull=0;
	String str_num="";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(new String[] { Main.class.getName() });
	}
	
	public void settings() {
		size(800,700);
	}
	
	public void setup(){
		textSize(128);
        f = createFont("Arial",16,true);
        //noStroke();
    }
	public void draw(){
		String gd=Integer.toString(dir.globalDepth);
		background(245, 148, 127);
		fill(0);
		textSize(24);
		text("Extendible Hashing Simulator",250,30,0);
		text("Enter key",40,180);
		textSize(24);
		text("Global Depth: "+gd,30,80);
		
		fill(255);
		rect(30,200,125,50);
		textFont(f,16);
    	fill(0);
    	text(str_num,40,230);
		int x=50;
		for(int i=0;i<dir.buckets.size();i++) {
			Bucket b=dir.buckets.get(i);
			
			fill(255);
        	rect(250,x,100,50);
        	
        	
        	int len=dir.globalDepth;
        	String bin="";
        	int k;
        	k=i;
        	int req=0;
        	while(k>0)
        	{
        		bin+=Integer.toString(k%2);
        		k/=2;
        		req++;
        	}
        	while(req<len) {bin+="0";req++;}
        	StringBuilder binary = new StringBuilder();
        	binary.append(bin);
        	binary=binary.reverse();
        	String fin="";
        	fin+=binary;
        	textFont(f,16);
        	fill(0);
        	text(fin,275,x+30);
        	
        	int y=0;
        	for(int j=0;j<b.bucketSize;j++) {
        		if(fl==1 && i==bn && j==ind && (b.records.get(j).value)==num)
        		{
        			fill(100,255,55);
        			rect(400+y,x,50,50);
        			y+=50;
        		}
        		else {
        			fill(255);
        			rect(400+y,x,50,50);
        			y+=50;
        		}
        	}
        	
        	String ld=Integer.toString(b.localDepth);
        	textFont(f,16);
        	fill(0);
        	text("Local Depth: "+ld + " id: "+b.id,400+y+15,x+30);
        	
        	y=0;
        	for(Pair p:b.records) {
        		if(fl==1 && p.value==num)
        		{
        			fill(100,255,55);
        			rect(400+y,x,50,50);
        		}
        		String s=Integer.toString(p.getVal());        		
        		textFont(f,16);
            	fill(0);
            	text(s,415+y,x+30);
            	y+=50;
        	}
        	
        	x+=55;
		}
		
		fill(200);
		rect(50,280,80,30);
		textFont(f,16);
    	fill(0);
    	text("insert",70,300);
		fill(200);
		rect(50,320,80,30);
		textFont(f,16);
    	fill(0);
    	text("delete",70,340);
		fill(200);
		rect(50,360,80,30);
		textFont(f,16);
    	fill(0);
    	text("search",70,380);
    	
    	if(fl==-1)
    	{
    		textFont(f,16);
        	fill(0);
        	text("Number not found",30,450);
    	}
    	
    	if(num%11==0 && bucketfull>3)
    	{
    		textFont(f,16);
        	fill(0);
        	text("Bucket is full",30,450);
    	}
    }
	
	public void keyPressed() {
		if(key>='0' && key<='9') {
			str_num+=key;
			redraw();
		}
		if(keyCode==(int)BACKSPACE && str_num.length()>0) {
			str_num=str_num.substring(0,str_num.length()-1);
			redraw();
		}
	}
	
	public void mousePressed() {
		if(mouseX>50 && mouseX<130 && mouseY>280 && mouseY<310)
		{
			if(!str_num.equals("")) {
			num = Integer.parseInt(str_num);
		    str_num = "";
		    System.out.println(num);
		    if(num%11==0) bucketfull++;
		    if(bucketfull<=3 || num%11>0) {
		    dir.insert(num%11, num);}
		    fl=0;
		    redraw();
		    }
		}
		if(mouseX>50 && mouseX<130 && mouseY>320 && mouseY<350)
		{
			if(!str_num.equals("")) {
			num = Integer.parseInt(str_num);
		    str_num = "";
		    System.out.println(num);
		    dir.delete(num%11, num);
		    fl=0;
		    redraw();}
		}
		if(mouseX>50 && mouseX<130 && mouseY>360 && mouseY<390)
		{
			if(!str_num.equals("")) {
			num = Integer.parseInt(str_num);
		    str_num = "";
		    System.out.println(num);
			Bucket b=dir.getBucket(num%11);
			bn=(key & ((1<<dir.globalDepth)-1));
			fl=-1;
			for(int i=0;i<b.records.size();i++)
			{
				if(b.records.get(i).value==num)
				{
					fl=1;
					ind=i;
				}
			}
			redraw();}
		}
	}
}
