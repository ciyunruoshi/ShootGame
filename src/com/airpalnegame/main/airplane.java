package com.airpalnegame.main;

import java.util.Random;

import com.airplace.view.shootgame;

public class airplane extends FlyObject {
	public airplane() {
		image = shootgame.airplaneimage;
		speed = 5;
		width= image.getWidth();
		height = image.getHeight();
		y = -height;
		Random te= new Random();
		x=te.nextInt(shootgame.WIDTH-width);
	}
	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void step() {
		y += speed;
	
	}

	@Override
	public boolean overbound() {
		return y>shootgame.HEIGHT;
	}
	
	public boolean isshoot(Bullet b){
		int xmin = this.x-b.getWidth();
		int xmax = this.x+this.width;
		int ymin = this.y-b.getHeight();
		int ymax = this.y+this.height;
		//´íÎó×ö·¨
//		int xmax = b.x-b.getWidth();
//		int ymin = b.y-b.getHeight();
//		int ymax = b.y+this.height;
//		
		return b.getX()>xmin&&b.getX()<xmax&&b.getY()<ymax&&b.getY()>ymin;
			
		}
	}

