package com.airpalnegame.main;

import com.airplace.view.shootgame;

public class Bullet extends FlyObject {
 
	public Bullet(int bulletx, int bullety) {
		image = shootgame.bulletimage;
		speed = 5;
		width= image.getWidth();
		height = image.getHeight();
		y = bullety;
		x = bulletx;
    }
	
	@Override
	public void step() {
		y -= speed;
	}

	@Override
	public boolean overbound() {
		return y< -height;
	}

}
