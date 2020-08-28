package game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GameEngine {	
	protected int intervalTime = 1000 / 30;
	public int GetInterval(){
		return intervalTime;
	}
	public void setFrame(int iTime) {
		intervalTime = 1000 / iTime;
	}
	public abstract void GameStart();
	public abstract void GameInit();
	public abstract void GamePaint();
	public abstract void GameLogic();
	public abstract void GameEnd();
	public abstract void GameKeyAction(KeyEvent e, int type);
	public abstract void GameMouseAction(MouseEvent e, int type);
	
}
