package com.mygdx.model;

import com.mygdx.state.Assets;

public class DungeonInfo {
	private String name;
	private int itemNumber;
	private String[] items;
	private String monsterName;
	private Monster monster;
	private String background;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	public Monster getMonster() {
		if (monster == null)
			monster = Assets.monsterMap.get(monsterName);
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
}
