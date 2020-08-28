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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chaofan.utils.ResourceUtils;

public class EndView extends JFrame implements ActionListener,MouseListener{
	
	public final static int WIDTH = 600, HEIGHT = 350;
	public final static int sx = 200,sy = 200;
	public static int scoreBoard = 0;
	private JPanel panel;
	private JButton regameButton, goMainButton;
	public EndView(){
	}
	public void endShow(final int score, final int time, final boolean isBeyond){
		panel = new JPanel(null){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image;
				try {
					if(isBeyond){
						scoreBoard = Tian_tian_engine.lastMaxScore;		
						JOptionPane.showMessageDialog(panel, "超越了最高记录 " + scoreBoard + " 分！", "恭喜",JOptionPane.WARNING_MESSAGE);				
					}
					if(score > 200){
						image = ResourceUtils.getImageFromFile(this, "/image/chou.png");
						g.drawImage(image, 0, 0, 600, 350, 0, 0, image.getWidth(panel), image.getHeight(panel), null);
						g.drawString(Integer.toString(score), 440, 275);
						g.drawString(Integer.toString(time), 440, 240);
					}else{
						image = ResourceUtils.getImageFromFile(this, "/image/pp.png");
						g.drawImage(image, 0, 0, 600, 350, 0, 0, image.getWidth(panel), image.getHeight(panel), null);
						g.drawString("不足200分,再接再厉喽！", 440, 280);
					}								
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		panel.setVisible(true);
		panel.setLocation(0, 0);
		panel.setSize(WIDTH, HEIGHT);
		
		regameButton = new JButton();
		regameButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh51.png"));
		regameButton.setLocation(220, 220);
		regameButton.setSize(60, 25);
		regameButton.addMouseListener(this);
		regameButton.addActionListener(this);
		panel.add(regameButton);
		
		goMainButton = new JButton();
		goMainButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh61.png"));
		goMainButton.setLocation(220, 250);
		goMainButton.setSize(60, 25);
		goMainButton.addMouseListener(this);
		goMainButton.addActionListener(this);
		panel.add(goMainButton);
		
		this.setContentPane(panel);
		
		this.setLocation(sx, sy);
		this.setIconImage(ResourceUtils.getImageFromFile(this, "/image/115.png"));
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == regameButton){
        	this.dispose();
        	new PlayView().playShow();
        }else if(e.getSource() == goMainButton){
        	this.dispose();
            new MainView().mainShow();
		}
    }
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == regameButton){
			regameButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh51.png"));
		}else if(e.getSource() == goMainButton){
			goMainButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh61.png"));
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == regameButton){
			regameButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh5.png"));
		}else if(e.getSource() == goMainButton){
			goMainButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/hh6.png"));
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
