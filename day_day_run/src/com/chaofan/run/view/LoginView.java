package com.chaofan.run.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.security.auth.login.AccountException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chaofan.utils.MusicPlay;
import com.chaofan.utils.ResourceUtils;

public class LoginView extends JFrame implements ActionListener {
	
	public final static int WIDTH = 1136, HEIGHT = 640;
	public final static int sx = 200,sy = 200;
	private final static String ACCONT = "chaofan";
	private final static String PASSWORD = "123";
	
	private JLabel nameLabel;
	private JLabel passwordLabel;
	private JTextField nameInputField;
	private JTextField passwordInputField;
	private JButton loginButton;
	private JButton escButton;
	private JPanel panel;
	public LoginView(){
		nameLabel = new JLabel();
		passwordLabel = new JLabel();
		nameInputField = new JTextField();
		passwordInputField = new JTextField();
		loginButton = new JButton();
		escButton = new JButton();
		panel = new JPanel(null){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = null;
				image = ResourceUtils.getImageFromFile(this, "/image/login.jpg");
				g.drawImage(image, 0, 0, null);
			}
		};
	}
	public void loginShow(){
		//标签添加
		nameLabel.setText("用户名");
		nameLabel.setSize(50, 20);
		nameLabel.setLocation(30, 150);
		nameLabel.setForeground(Color.BLUE);
		panel.add(nameLabel);
		
		passwordLabel.setText("密码");
		passwordLabel.setSize(50, 20);
		passwordLabel.setLocation(30, 200);
		passwordLabel.setForeground(Color.BLUE);
		panel.add(passwordLabel);
		
		nameInputField.setLocation(90, 150);
		nameInputField.setSize(150, 30);
		nameInputField.setText("chaofan");
		panel.add(nameInputField);
		
		passwordInputField.setLocation(90, 200);
		passwordInputField.setSize(150, 30);
		passwordInputField.setText("123");
		panel.add(passwordInputField);
		
		loginButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/denglu.gif"));
		loginButton.setLocation(30, 250);
		loginButton.setSize(68, 20);
		loginButton.addActionListener(this);
		panel.add(loginButton);
		escButton.setIcon(ResourceUtils.getImageIconFromFile(this, "/image/quxiao.gif"));
		escButton.setLocation(116, 250);
		escButton.setSize(68, 20);
		escButton.addActionListener(this);
		//escButton.setText("退出");
		panel.add(escButton);
		
		panel.setVisible(true);
		panel.setLocation(0, 0);
		panel.setSize(599, 370);
		
		this.setContentPane(panel);
		this.setIconImage(ResourceUtils.getImageFromFile(this, "/image/115.png"));
		this.setVisible(true);
		this.setSize(599, 360);
		this.setResizable(false);
		this.setLocation(sx, sy);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		new MusicPlay().musicPlay();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            if(nameInputField.getText().equals(ACCONT)
            		&& passwordInputField.getText().equals(PASSWORD)){
            	JOptionPane.showMessageDialog(null, "登录成功！");
            	this.dispose();
                new MainView().mainShow();
            }else{
            	JOptionPane.showMessageDialog(null, "登录失败！");
            }
        }
        if(e.getSource() == escButton){
            this.dispose();
            System.exit(0);
        }
    }
}
