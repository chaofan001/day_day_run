package com.chaofan.run.sprit;

import game.Rect;

import java.awt.Image;
import java.io.Serializable;

public class Sprit implements Serializable{	
	
	public final static int STATIC = 0;
	public final static int MOVING = 1;
	
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
	protected String imagePath;
	protected String[] framePath;
	protected int width, height;
	protected int cx, cy;
	protected int frameCount;
	protected int loopCount;
	protected int actionStatus;
	protected int direction;
	protected int speed;
	
	protected boolean isDead;
	
	public Sprit(){
		frameCount = 0;
		loopCount = 0;
		actionStatus = STATIC;
		direction = RIGHT;
	}
	

	public Rect getRect(){
		Rect rect = new Rect();
		rect.setLeft(cx);
		rect.setTop(cy);
		rect.setRight(cx + width);
		rect.setBottom(cy + height);
		return rect;
	}

	public boolean isDead() {
		return isDead;
	}


	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}


	public int getSpeed() {
		return speed;
	}




	public void setSpeed(int speed) {
		this.speed = speed;
	}




	public int getActionStatus() {
		return actionStatus;
	}




	public void setActionStatus(int actionStatus) {
		this.actionStatus = actionStatus;
	}




	public int getDirection() {
		return direction;
	}




	public void setDirection(int direction) {
		this.direction = direction;
	}




	public int getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String[] getFramePath() {
		return framePath;
	}
	public void setFramePath(String[] framePath) {
		this.framePath = framePath;
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
	public int getCx() {
		return cx;
	}
	public void setCx(int cx) {
		this.cx = cx;
	}
	public int getCy() {
		return cy;
	}
	public void setCy(int cy) {
		this.cy = cy;
	}
	public int getFrameCount() {
		return frameCount;
	}
	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}
	
	public void move(){
		switch (direction) {
		case UP:
			
			break;
		case DOWN:
			
			break;
		case LEFT:
			this.cx -= speed;
			break;
		case RIGHT:
			this.cx += speed;
			break;
		default:
			break;
		}
	}
	
}
