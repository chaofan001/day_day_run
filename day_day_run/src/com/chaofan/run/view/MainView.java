package com.chaofan.run.view;

import game.Tian_tian_engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chaofan.utils.ResourceUtils;

public class MainView extends JFrame implements ActionListener,MouseListener{
	
	public final static int WIDTH = 1136, HEIGHT = 640;
	public final static int sx = 200,sy = 200;
	private JPanel panel;
	private JButton startButton, helpButton, exitButton, continueButton;
	private boolean haveSave = false;
	public MainView(){
	}
	public void mainShow(){
		/*//标签添加
		nameLabel.setText("用户名");
		nameLabel.setSize(50, 20);
		nameLabel.setLocation(30, 150);
		panel.add(nameLabel);
		
		passwordLabel.setText("密码");
		passwordLabel.setSize(50, 20);
		passwordLabel.setLocation(30, 200);
		panel.add(passwordLabel);
		
		nameInputField.setLocation(90, 150);
		nameInputField.setSize(150, 30);
		panel.add(nameInputField);
		
		passwordInputField.setLocation(90, 200);
		passwordInputField.setSize(150, 30);
		panel.add(passwordInputField);
		
		loginButton.setIcon(new ImageIcon("./src/image/denglu.gif"));
		loginButton.setLocation(30, 250);
		loginButton.setSize(68, 20);
		//loginButton.setText("登录");
		panel.add(loginButton);*/
		
		panel = new JPanel(null){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image;
				image = ResourceUtils.getImageFromFile(this, "/image/main.png");
				g.drawImage(image, 0, 0, null);
			}
		};
		panel.setVisible(true);
		panel.setLocation(0, 0);
		panel.setSize(WIDTH, HEIGHT);
		
		continueButton = new JButton();
		continueButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh44.png"));
		continueButton.setLocation(350, 290);
		continueButton.setSize(150, 40);
		continueButton.addMouseListener(this);
		continueButton.addActionListener(this);
		panel.add(continueButton);
		
		startButton = new JButton();
		startButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh12.png"));
		startButton.setLocation(350, 350);
		startButton.setSize(150, 40);
		startButton.addMouseListener(this);
		startButton.addActionListener(this);
		panel.add(startButton);
		
		helpButton = new JButton();
		helpButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh21.png"));
		helpButton.setLocation(350, 410);
		helpButton.setSize(150, 40);
		helpButton.addMouseListener(this);
		helpButton.addActionListener(this);
		panel.add(helpButton);
		
		exitButton = new JButton();
		exitButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh31.png"));
		exitButton.setLocation(350, 470);
		exitButton.setSize(150, 40);
		exitButton.addMouseListener(this);
		exitButton.addActionListener(this);
		panel.add(exitButton);
		
		this.setContentPane(panel);
		
		this.setLocation(sx, sy);
		this.setIconImage(ResourceUtils.getImageFromFile(this, "/image/115.png"));
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		File file = new File("save");
		if(!file.exists()){
			file.mkdir();
		}
		if(new File("save/player.txt").exists()){
			haveSave = true;
		}
		
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
        	this.dispose();
        	Thread thread = new Thread(new LoadingView());
        	thread.start();
        }else if(e.getSource() == helpButton){
        	int max = Tian_tian_engine.getMaxScore(this);
        	JOptionPane.showMessageDialog(panel, "这是帮助,目前最高分是" + max, "帮助",JOptionPane.WARNING_MESSAGE);  
		}else if(e.getSource() == exitButton){
            this.dispose();
            System.exit(0);
		}else if(e.getSource() == continueButton){
			if(haveSave){
				this.dispose();
	        	Tian_tian_engine.readSave = true;
	        	Thread thread = new Thread(new LoadingView());
	        	thread.start();
			}
		}
    }
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == startButton){
			startButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh12.png"));
		}else if(e.getSource() == helpButton){
			helpButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh21.png"));
		}else if(e.getSource() == exitButton){
			exitButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh31.png"));
		}else if(e.getSource() == continueButton){
			continueButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh44.png"));
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == startButton){
			startButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh1.png"));
		}else if(e.getSource() == helpButton){
			helpButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh2.png"));
		}else if(e.getSource() == exitButton){
			exitButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh3.png"));
		}else if(e.getSource() == continueButton){
			if(haveSave){
				continueButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh43.png"));
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
