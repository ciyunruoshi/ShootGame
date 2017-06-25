package com.airpalnegame.main;

import com.airplace.view.shootgame;

public class Hero extends FlyObject {

	//private BufferedImage[] images= {};
	private int fire;
	private int life;
	private Bullet[] bullets;
	private int totalgrade ;
	
	public Hero() {
		image = shootgame.hero0;
		width= image.getWidth();
		height = image.getHeight();
		y = shootgame.HEIGHT-width-50;
		x = 200;
		fire = 1;
		life = 3;
		totalgrade = 0;
		//images = new BufferedImage[]{shootgame.hero0,shootgame.hero1};	
	}
	
	public void addgrade(int x){
		totalgrade +=x;
	}
	
	public int gettotalgrade(){
		return totalgrade;
	}
	
	public void addfire(){
		fire = 2;
	}
	
	public void subfire(){
		fire =1;
	}
	
	public void addlife(){
		if(life<5){
			life++;
		}
		
	}
	

	public void substractlife(){
		life--;
	}

	public int getlife() {
		return life;
	}
	
	
	public void moveTo(int x,int y){
		this.x= x-this.width/2;
		this.y= y-this.height/2;
		if(this.x > shootgame.WIDTH-width){
			this.x=shootgame.WIDTH-width;
		}else if(this.x<0){
			this.x = 0;
		}
		if(this.y > shootgame.HEIGHT-height){
			this.y=shootgame.HEIGHT-width;
		}else if(this.y<0){
			this.y = 0;
		}
	}
	
	public Bullet[] shootbullet(){
		int xstep = this.width/4;
		int ystep = 20;
		if(fire == 1){
			bullets = new Bullet[1];
			bullets[0] = new Bullet(x+xstep*2, y-ystep);
		}else if(fire == 2){
			bullets = new Bullet[2];
			bullets[0] = new Bullet(x+xstep, y-ystep);
			bullets[1] = new Bullet(x+xstep*3, y-ystep);	
		}
		return bullets;
	}

	public boolean hit(FlyObject other){
		int x1 = other.x-this.width;
		int x2 = other.x+other.width;
		int y1 = other.y-this.height;
		int y2 = other.y+other.height;
		
		return x<x2&&x>x1&&y<y2&&y>y1;
		
	}


	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean overbound() {
		// TODO Auto-generated method stub
		return false;
	}

}
