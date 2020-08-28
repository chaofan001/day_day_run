package com.chaofan.run.sprit;

import java.io.Serializable;

import game.Rect;

public class Shell extends Sprit implements Serializable{
	public Shell(){
		 shellInit();
	}
	
	public void shellInit(){
		imagePath = "/image/daodan.png";
		width = 100;
		height = 52;
		speed = 10;
		direction = LEFT;
	}
	public Rect getRect(){
		Rect rect = new Rect();
		rect.setLeft(cx + 30);
		rect.setTop(cy + 10);
		rect.setRight(cx + width - 30);
		rect.setBottom(cy + height - 10);
		return rect;
	}

}
