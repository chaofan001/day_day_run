package com.chaofan.run.sprit;

import java.io.Serializable;
import java.util.Set;

public class GameArchives implements Serializable{
	private Player player;
	private Set<Shell> enemys;
	private Set<Collectible> collections;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Set<Shell> getEnemys() {
		return enemys;
	}
	public void setEnemys(Set<Shell> enemys) {
		this.enemys = enemys;
	}
	public Set<Collectible> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collectible> collections) {
		this.collections = collections;
	}
	
}
