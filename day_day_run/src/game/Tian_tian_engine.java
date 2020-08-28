package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Desktop.Action;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chaofan.run.sprit.Collectible;
import com.chaofan.run.sprit.Player;
import com.chaofan.run.sprit.Shell;
import com.chaofan.run.sprit.Sprit;
import com.chaofan.utils.MusicPlay;
import com.chaofan.utils.ResourceUtils;
import com.sun.org.apache.regexp.internal.recompile;

public class Tian_tian_engine extends GameEngine{
	
	private static String backgroundPath = "/image/gbg.jpg"; 
	private static String background2Path = "/image/11.jpg"; 
	private static String scoreBoardPath = "/image/a12.png"; 
	
	private static int LOOPFRAME = 1;
	private	 int rollSpeed = 10;
	
	private JPanel panel;
	private Image dbBuffer;
	private int width,height;
	private boolean isRunning;
	private int bgLeft;
	private int bgWidth, bgHeight;
	private int finalScore;
	private int difficulty;
	private boolean beyond = false;
	
	private Image background;
	private Image collection;
	private Image[] playerImgs;
	private Image[] collImages;
	private Image enemyImg;
	private Image scoreBoard;
	private Image stopImg;	
	
	private Player player;
	private Set<Shell> enemys;
	private Set<Collectible> collections;
	private int status;
	private long frameCount = 0;
	private int goingTime;
	
	public static int lastMaxScore;
	public static boolean readSave = false;
	
	
	public Tian_tian_engine(){
		
	}
	public Tian_tian_engine(JPanel canvas,int width, int height){
		this.panel = canvas;
		this.width = width;
		this.height = height;
		
		this.status = 0;
		
		enemys = new HashSet<Shell>();
		collections = new HashSet<Collectible>();
		collImages = new Image[6];
	}
	
	public void run(){
		GameStart();
	}
	
	@Override
	public void GameStart() {
		GameInit();
		threadPaint();
		while(isRunning){
			frameCount++;
			if(frameCount % 30 ==0)
				goingTime++;
			if(frameCount > 9999)
				frameCount = 0;
			GameLogic();
			//GamePaint();
			GameEnd();
					
			/*沉睡每帧间隔时间*/
			try{
				Thread.sleep(intervalTime);
			}catch (Exception e) {
			}
		}
	}
	
	@Override
	public void GameInit() {
		player = new Player();
		
		try {
			background = ResourceUtils.getImageFromFile(this, backgroundPath);
		
			playerImgs = new Image[player.getFramePath().length];
			for(int i = 0 ; i < 9 ; i++){
				playerImgs[i] = ResourceUtils.getImageFromFile(this,player.getFramePath()[i]);
			}
			
			for(int i = 0 ; i < 6 ; i++){
				collImages[i] = ResourceUtils.getImageFromFile(this, "/image/" + (i+21) + ".png");
			}
			
			enemyImg = ResourceUtils.getImageFromFile(this, "/image/daodan.png");
			scoreBoard = ResourceUtils.getImageFromFile(this, scoreBoardPath);
			stopImg = ResourceUtils.getImageFromFile(this, "/image/a9.png");
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		isRunning = true;
		intervalTime = 1000 / 40;
		goingTime = 0;
		bgWidth = background.getWidth(panel);
		bgHeight = background.getHeight(panel);
		bgLeft = 0;
		dbBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		status = 1;
		difficulty = 1;
		new MusicPlay().gameMusicPlay();

		if(readSave && new File("save/player.txt").exists()){
			try {
				load();
				readSave = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void GamePaint() {
		drawBackground();
		drawCollections();
		drawAnamys();
		drawPlayer();
		Graphics g;		
		
		if(status == 2){
			g = dbBuffer.getGraphics();
			g.setColor(new Color((float)0.5, (float)0.5, (float)0.5, (float)0.5));
			g.fillRect(0, 0, width, height);
			g.drawImage(stopImg, width / 2 - 20, height / 2 - 20, panel);
		}
	
		g = panel.getGraphics();
		g.drawImage(dbBuffer, 0, 0, panel);
		
		while(status == 2){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void drawBackground(){
		Graphics g = dbBuffer.getGraphics();
		/*int w1, w2;
		w1 = width - bgLeft;
		g.drawImage(background, 0, 0, w1, height,
				(bgWidth*bgLeft/width), 0, bgWidth, bgHeight, panel);
		w2 = bgLeft;
		g.drawImage(background, w1, 0, width, height,
				0, 0, (int)(bgWidth*w2/width), bgHeight, panel);*/
		//g.drawImage(background, bgLeft, 0, width, height, panel);
		//g.drawImage(background, width + bgLeft, 0, width, height, panel);
		g.drawImage(background, bgLeft, 0, width, height, panel);
		g.drawImage(background, width + bgLeft, 0, width, height, panel);
		//bgLeft += rollSpeed;
		bgLeft -= rollSpeed;
		//if(bgLeft >= width)
		//	bgLeft = 0;
		if(bgLeft <= -width)
			bgLeft = 0;
	}
	private void drawCollections(){
		Graphics g = dbBuffer.getGraphics();
			try{
				for(Collectible c : collections){
					g.drawImage(collImages[c.getType() - 1], c.getCx(), c.getCy(), panel);
				}
			}catch (Exception e) {
			}	
	}
	private void drawAnamys(){
		Graphics g = dbBuffer.getGraphics();
		Iterator<Shell> it2 = enemys.iterator();
		try{
			while(it2.hasNext()){
				Shell s = it2.next();
				if(!s.isDead())
					g.drawImage(enemyImg, s.getCx(), s.getCy(), s.getWidth(),s.getHeight(),panel);
		}
		}catch (Exception e) {
		}
	}
	private void drawPlayer(){
		Graphics g = dbBuffer.getGraphics();
		if(player != null){
			g.drawImage(playerImgs[player.getFrameCount()], player.getCx(), player.getCy(), panel);
			player.setLoopCount(player.getLoopCount() + 1);
			if(player.getLoopCount() > LOOPFRAME){
				player.setFrameCount(player.getFrameCount() + 1);
				player.setLoopCount(0);
			}
			if(player.getFrameCount() >= playerImgs.length){
				player.setFrameCount(0);
			}
			
			g.drawImage(scoreBoard, 0, 0, panel);
			g.setFont(new Font("微软雅黑", Font.BOLD, 20));
			g.drawString("得分： " + player.getScore(), 20, 40);
		}
	} 
	
	@Override
	public void GameLogic() {
		if(status == 1){
			collisionCheck();
		
			playerLogic();
			collectionLogic();
			enemyLogic();
		}	
	}
	public void playerLogic(){
		if(player != null){
			switch(player.getActionStatus()){
			case Sprit.STATIC:
				break;
			case Sprit.MOVING:
				player.move();			
			}
			if(player.jumpStatus > 0){
			player.jump();
			}
			if(difficulty < 2 && player.getScore() >= 200){
				background = ResourceUtils.getImageFromFile(this, background2Path);;
				difficulty = 2;
			}
		}
	}
	
	public void collectionLogic(){
		Collectible collectible;
		if(frameCount % 20 == 0){
			Random random = new Random(new Object().hashCode());
			if(frameCount % 40 == 0){
				if(random.nextInt(100) <= 70){
					collectible = new Collectible();
					collectible.setCx(width + collectible.getWidth());
					collectible.setCy(random.nextInt(height - collectible.getHeight() - 350) + 170);
					collections.add(collectible);
				}
			}else{
				if(random.nextInt(100) <= 40){
					collectible = new Collectible();
					collectible.setCx(width + collectible.getWidth());
					collectible.setCy(random.nextInt(height - collectible.getHeight() - 350) + 170);
					collections.add(collectible);
				}
			}
		}
		
		Iterator<Collectible> it = Collections.synchronizedCollection(collections).iterator();
		while(it.hasNext()){
			Collectible c = it.next();
			c.move();
			if(c.getCx() + c.getWidth() < 0){
				it.remove();
			}
		}
	}
	
	public void enemyLogic(){
		Shell shell;
		if(frameCount % 30 == 0){
			Random random = new Random(new Object().hashCode());
			if(frameCount % 50 == 0){
				if(random.nextInt(100) <= 60){
					shell = new Shell();
					if(difficulty == 1){						
					}else if(difficulty == 2){
						shell = new Shell();
						shell.setSpeed(shell.getSpeed() + 2);
						shell.setWidth(shell.getWidth() + 10);
						shell.setHeight(shell.getHeight() + 10);
					}
					shell.setCx(width + shell.getWidth());
					shell.setCy(random.nextInt(height - shell.getHeight() - 300) + 150);
					enemys.add(shell);
				}
			}else{
				if(random.nextInt(100) <= 30){
					shell = new Shell();
					if(difficulty == 1){						
					}else if(difficulty == 2){
						shell = new Shell();
						shell.setSpeed(shell.getSpeed() + 2);
						shell.setWidth(shell.getWidth() + 10);
						shell.setHeight(shell.getHeight() + 10);
					}
					shell.setCx(width + shell.getWidth());
					shell.setCy(random.nextInt(height - shell.getHeight() - 350) + 200);
					enemys.add(shell);
				}
			}
		}
		
		Iterator<Shell> it = Collections.synchronizedCollection(enemys).iterator();
		while(it.hasNext()){
			Shell s = it.next();
			s.move();
			if(s.getCx() + s.getWidth() < 0){
				it.remove();
				s.setDead(true);
			}
		}
	}
	
	public void collisionCheck(){
		if(player.getCx() < 0){
			player.setCx(0);
		}
		if(player.getCx() > width - player.getWidth()){
			player.setCx(width - player.getWidth());
		}
		Rect pRect = player.getRect();
				
		Iterator<Collectible> it1 = Collections.synchronizedCollection(collections).iterator();
		while(it1.hasNext()){
			Collectible c = it1.next();
			if(isCollided(pRect, c.getRect())){
				player.setScore(player.getScore() + c.getScore());
				it1.remove();
			}
		}
		
		Iterator<Shell> it2 = enemys.iterator();
		while(it2.hasNext()){
			Shell s = it2.next();
			/*if(isCollided(pRect, s.getRect())){
				status = -1;
			}*/
			Rect[] rs = player.getRect2();
			if(isCollided(rs[0], s.getRect()) || isCollided(rs[1], s.getRect())){
				status = -1;
			}
		}
	}
	
	public boolean isCollided(Rect r1, Rect r2){
		if( (r1.getBottom() >= r2.getTop()) 
				&& (r1.getTop() <= r2.getBottom())
				&& (r1.getRight() >= r2.getLeft())
				&& (r1.getLeft() <= r2.getRight()) ){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void GameEnd() {
		if(status == -1){
			isRunning = false;
			this.finalScore = player.getScore();
			int max;
			try {
				max = getMaxScore(this);
				if(player.getScore() > max){
					beyond = true;
					lastMaxScore = max;
					saveScore();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			new MusicPlay().musicPlay();
			//player = null;
			new File("save/player.txt").delete();
			new File("save/shell.txt").delete();
			new File("save/coll.txt").delete();
			isRunning = false;
		}
	}

	public void saveScore() throws IOException{
		File file = new File("save/score.txt");
		FileOutputStream fos = new FileOutputStream(file);
		String s = "" + player.getScore();
		fos.write(s.getBytes());
		fos.close();
	}
	public static int getMaxScore(Object o){
		File file = null;
		try {
			file = new File("save/score.txt");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 0;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int result;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buff = new byte[128];
			int count;
			StringBuilder sb = new StringBuilder();
			while((count = fis.read(buff)) != -1){
				sb.append(new String(buff, 0, count));
			}
			String s =sb.toString();
			result = Integer.valueOf(sb.toString());
		}catch (Exception e) {
			return 0;
		}
		return result;
	}
	
	@Override
	public void GameKeyAction(KeyEvent e, int type) {
		if(type == 0){	//按下按键事件
			if(e.getKeyCode() == e.VK_ESCAPE){
				status = -1;
			}else if(e.getKeyCode() == e.VK_LEFT){
				player.setActionStatus(Sprit.MOVING);
				player.setDirection(Sprit.LEFT);
			}else if(e.getKeyCode() == e.VK_RIGHT){
				player.setActionStatus(Sprit.MOVING);
				player.setDirection(Sprit.RIGHT);
			}else if(e.getKeyCode() == e.VK_UP){
				if(player.jumpStatus < 0)
					player.jumpStatus = player.UPING;
			}else if(e.getKeyCode() == e.VK_SPACE){
				if(status == 1){
					try {
						save();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					status = 2;
				}else if(status == 2){
					status = 1;
				}
			}
		}else{			//松开按键事件
			if(e.getKeyCode() == e.VK_ESCAPE){
				status = -1;
			}else if(e.getKeyCode() == e.VK_LEFT){
				player.setActionStatus(Sprit.STATIC);
			}else if(e.getKeyCode() == e.VK_RIGHT){
				player.setActionStatus(Sprit.STATIC);
			}
		}
	}

	@Override
	public void GameMouseAction(MouseEvent e, int type) {

	}
	public int getFinalScore() {
		return finalScore;
	}

	public void threadPaint(){
		Thread thread = new Thread(){
			@Override
			public void run(){
				while(isRunning){			
					GamePaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	public synchronized void lock(){
		
	}
	public int getGoingTime() {
		return goingTime;
	}
	public void setGoingTime(int goingTime) {
		this.goingTime = goingTime;
	}
	
	public void save() throws FileNotFoundException, IOException{
		File f1 = new File("save/player.txt"),
			f2 = new File("save/shell.txt"),
			f3 = new File("save/coll.txt");
		if(!f1.exists()){
			f1.createNewFile();
		}
		if(!f2.exists()){
			f2.createNewFile();
		}
		if(!f3.exists()){
			f3.createNewFile();
		}
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("save/player.txt"));
		oo.writeObject(player);
		oo.close();
		oo = new ObjectOutputStream(new FileOutputStream("save/shell.txt"));
		oo.writeObject(enemys);
		oo.close();
		oo = new ObjectOutputStream(new FileOutputStream("save/coll.txt"));
		oo.writeObject(collections);
		oo.close();
	}
	
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save/player.txt"));
		player = (Player) ois.readObject();
		ois.close();
		ois = new ObjectInputStream(new FileInputStream("save/shell.txt"));
		enemys = (Set<Shell>) ois.readObject();
		ois.close();
		ois = new ObjectInputStream(new FileInputStream("save/coll.txt"));
		collections = (Set<Collectible>) ois.readObject();
		ois.close();
	}
	public boolean isReadSave() {
		return readSave;
	}
	public void setReadSave(boolean readSave) {
		this.readSave = readSave;
	}
	public boolean isBeyond() {
		return beyond;
	}
	public void setBeyond(boolean beyond) {
		this.beyond = beyond;
	}

}
