package com.chaofan.utils;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceUtils {
	public static Image getImageFromFile(Object o, String path){
		Image img = null;
		try {
			img = ImageIO.read(o.getClass().getResource(path).toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}
	public static ImageIcon getImageIconFromFile(Object o, String path){
		ImageIcon img = null;
		try {
			img = new ImageIcon(o.getClass().getResource(path).toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}
	
}
