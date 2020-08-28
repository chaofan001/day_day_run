package com.chaofan.run.sprit;

import game.Rect;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Player extends Sprit implements Serializable{
	public final static int DONE = -1;
	public final static int UPING = 1;
	public final static int DOWNING = 2;
	public int jumpStatus = DONE;
	private int jumpAccelerate = 2;
	private int jumpSpeed = 26;
	private int score = 0;
	public Player(){
		 playerInit();
	}
	
	public void playerInit(){
		framePath = new String[9];
		for(int i = 0 ; i < 9 ; i++){
			framePath[i] = "/image/" + (i+1) + ".png";
		}
		width = 102;
		height = 111;
		cx = 200;
		cy = 340;
		speed = 15;
	}
	
	public void jump(){
		switch (jumpStatus) {
		case UPING:
			cy -= jumpSpeed;
			jumpSpeed -= jumpAccelerate;
			if(jumpSpeed <= 0){
				jumpStatus = DOWNING;
			}
			break;
		case DOWNING:
			cy += jumpSpeed;
			jumpSpeed += jumpAccelerate;
			if(jumpSpeed >26){
				jumpSpeed = 26;
				jumpStatus = DONE;
			}
			break;
		default:
			break;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public Rect getRect(){
		Rect rect = new Rect();
		rect.setLeft(cx + 30);
		rect.setTop(cy );
		rect.setRight(cx + width);
		rect.setBottom(cy + height);
		return rect;
	}
	
	public Rect[] getRect2(){
		Rect[] rects = new Rect[2];
		Rect rect1 = new Rect(),
			rect2 = new Rect();
		rect1.setLeft(cx + 30);
		rect1.setTop(cy );
		rect1.setRight(cx + width);
		rect1.setBottom(cy + height / 2);
		
		rect2.setLeft(cx);
		rect2.setTop(cy + height / 2);
		rect2.setRight(cx + width - 30);
		rect2.setBottom(cy + height);
		
		rects[0] = rect1;
		rects[1] = rect2;
		
		return rects;
	}
	
}
