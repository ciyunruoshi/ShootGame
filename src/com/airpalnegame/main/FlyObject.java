package com.airpalnegame.main;

import java.awt.image.BufferedImage;

public abstract class FlyObject {
	
	protected BufferedImage image; 
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int speed;
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public abstract void step();
	public abstract boolean overbound();
	
}