package com.airpalnegame.main;

import java.util.Random;

import com.airplace.view.shootgame;

public class bee extends FlyObject {
	private int yspeed ;
	private int AWARDTYPE ;//0 代表双倍火力 1代表生命值增加
	public bee() {
		// TODO Auto-generated constructor stub
		image = shootgame.beeimage;
		width= image.getWidth();
		height = image.getHeight();
		speed = 5;
		yspeed = 6;
		y = -height;
		Random te= new Random();
		x=te.nextInt(shootgame.WIDTH-width);
		AWARDTYPE = te.nextInt(2);
		System.out.println("奖励为："+AWARDTYPE);
	}
	
	
	
	public int getaward() {
		return AWARDTYPE;
	}


	@Override
	public void step() {
//		if (speed > shootgame.WIDTH) {
//			speed = -5;
//		} else if(speed < 0){
//			speed =5;
//		}   出现错误
		
		if (x >= (shootgame.WIDTH-width)) {
			speed = -5;
		} else if(x <= 0){
			speed =5;
		} 
		x +=speed;
		y +=yspeed;

	}

	@Override
	public boolean overbound() {
		// TODO Auto-generated method stub
		return y>shootgame.HEIGHT;
	}

	public boolean isshoot(Bullet b){
		int xmin= this.x-b.getWidth();
		int xmax = this.x+this.getWidth();
		int ymin = this.y-b.getHeight();
		int ymax = this.y+this.height;
		
		return b.getX()>xmin&&b.getX()<xmax&&b.getY()<ymax&&b.getY()>ymin;
			
		}
}
