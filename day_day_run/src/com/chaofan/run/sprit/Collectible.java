package com.chaofan.run.sprit;

import java.io.Serializable;
import java.util.Random;

public class Collectible extends Sprit  implements Serializable{
	
	private int score;
	private int type;
	public Collectible(){
		collectibleInit();
	}
	
	public void collectibleInit(){
		Random random = new Random(new Object().hashCode());
		double p = random.nextDouble();
		
		if(p < 0.05){
			type = 1;		//0.05
		}else if(p < 0.05){
			type = 2;		//0.1
		}else if(p < 0.2){
			type = 3;		//0.12
		}else if(p < 0.35){
			type = 4;		//0.18
		}else if(p < 0.6){
			type = 5;		//0.2
		}else{			
			type = 6;		//0.35
		}
		
		imagePath = "/image/" + (type + 20) + ".png";
		
		if(type == 1){
			score = 100;
		}else if(type == 2){
			score = 30;
		}else if(type == 3){
			score = 20;
		}else if(type == 4){
			score = 15;
		}else if(type == 5){
			score = 10;
		}else{
			score = 5;
		}
		
		width = 30;
		height = 30;
		speed = 7;
		direction = LEFT;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
