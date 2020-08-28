package com.chaofan.run.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.chaofan.utils.MusicPlay;
import com.chaofan.utils.ResourceUtils;

public class LoadingView extends JFrame implements Runnable{
	public final static int WIDTH = 568, HEIGHT = 420;
	public final static int sx = 200,sy = 200;
	
	private JLabel bckLabel;
	private JProgressBar loadBar;
	private JPanel panel;
	public LoadingView(){
		bckLabel = new JLabel();
		panel = new JPanel(null);
		
		//标签添加
		bckLabel.setText("用户名");
		bckLabel.setLocation(0,0);
		bckLabel.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hbg.jpg"));
		bckLabel.setSize(WIDTH, HEIGHT -100);
		panel.add(bckLabel);
		
		loadBar = new JProgressBar(0, 100);
		loadBar.setSize(WIDTH, 100);
		loadBar.setLocation(0, HEIGHT - 100);
		loadBar.setValue(0);
		loadBar.setBackground(Color.ORANGE);
		loadBar.setStringPainted(true);
		panel.add(loadBar);
		
		panel.setVisible(true);
		panel.setLocation(0, 0);
		panel.setSize(WIDTH, HEIGHT);
		panel.setFocusable(false);
		
		this.setContentPane(panel);
		this.setIconImage(ResourceUtils.getImageFromFile(this, "/image/115.png"));	
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocation(sx, sy);
		this.setFocusable(false);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void run() {
		int[] nums = {0, 5, 20, 60, 80, 97, 98, 99, 100};
		for(int i : nums){	
			loadBar.setValue(i);
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(i == 100){
				new PlayView().playShow();
				this.dispose();
			}
		}
		
	}
	
}
