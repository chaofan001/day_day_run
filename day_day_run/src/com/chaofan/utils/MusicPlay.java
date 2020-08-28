package com.chaofan.utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlay {
	private static String musicPath = "/music/bgm.wav";
	private static String musicPath2 = "/music/run.wav";
	public static AudioClip audioClip;
	public static Player player = null;
	public void musicPlay(){
		try {
			if(audioClip !=null)
				audioClip.stop();
			audioClip = Applet.newAudioClip(getClass().getResource(musicPath).toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		audioClip.loop();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					File file = new File(musicPath);
//					FileInputStream fis = new FileInputStream(file);
//					BufferedInputStream stream = new BufferedInputStream(fis);
//					player = new Player(stream);
//					player.play();
//				} catch (Exception e) {
//
//					// TODO: handle exception
//				}
//			}
//		}).start(); 
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		player.close();*/
	}
	public void gameMusicPlay(){
		try {
			if(audioClip !=null)
				audioClip.stop();
			audioClip = Applet.newAudioClip(getClass().getResource(musicPath2).toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		audioClip.loop();
	}
}
