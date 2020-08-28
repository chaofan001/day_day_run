package com.chaofan.run.view;

import game.Tian_tian_engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chaofan.utils.MusicPlay;
import com.chaofan.utils.ResourceUtils;

public class PlayView extends JFrame implements KeyListener{
	public final static int WIDTH = 1136, HEIGHT = 640;
	public final static int sx = 200,sy = 200;
	private JPanel panel;
	
	private Tian_tian_engine game;
	public PlayView(){
	}
	public void playShow(){
		
		panel = new JPanel(null);
		panel.setVisible(true);
		panel.setLocation(0, 0);
		panel.setSize(WIDTH, HEIGHT);
		
		this.setContentPane(panel);
		
		this.setLocation(sx, sy);
		this.setIconImage(ResourceUtils.getImageFromFile(this, "/image/115.png"));
		this.setResizable(false);		//不可调整大小
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setFocusable(true);		//默认焦点
		this.setUndecorated(true);		//消除边框
		this.setVisible(true);
		
		final JFrame jFrame = this;
	
		Thread t = new Thread(){
		      @Override
		      public void run() {
		    	game = new Tian_tian_engine(panel, WIDTH, HEIGHT);
				game.GameStart();
			 	JOptionPane.showMessageDialog(panel, "游戏结束！", "Over",JOptionPane.WARNING_MESSAGE);  
				new EndView().endShow(game.getFinalScore(), game.getGoingTime(), game.isBeyond());
				jFrame.dispose();
		      }
		    };
		t.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.GameKeyAction(e, 0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.GameKeyAction(e, 1);
	}
	
}
