package com.chaofan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Set;

import com.chaofan.run.sprit.Collectible;
import com.chaofan.run.sprit.GameArchives;
import com.chaofan.run.sprit.Player;
import com.chaofan.run.sprit.Shell;

public class GamePersisentUtil {
	public static void save(Player pl, Set<Collectible> colls, Set<Shell> enemies){
		try{
			GameArchives ga = new GameArchives();
			ga.setPlayer(pl);
			ga.setCollections(colls);
			ga.setEnemys(enemies);
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("./src/save/archive.txt"));
			oo.writeObject(ga);
			oo.close();
		}catch (Exception e) {
		}
	}
	
	public static GameArchives getSaved(){
		GameArchives ga = null;
		try {
			/*ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/save/player.txt"));
			ga.setPlayer((Player) ois.readObject());
			ois.close();
			ois = new ObjectInputStream(new FileInputStream("./src/save/shell.txt"));
			ga.setEnemys((Set<Shell>) ois.readObject());
			ois.close();
			ois = new ObjectInputStream(new FileInputStream("./src/save/coll.txt"));
			ga.setCollections((Set<Collectible>) ois.readObject());
			ois.close();*/
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/save/archive.txt"));
			ga = (GameArchives) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ga;
		
	}
}
