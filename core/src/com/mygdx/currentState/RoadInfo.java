package com.mygdx.currentState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.model.Monster;
import com.mygdx.state.Assets;

@Component
public class RoadInfo {
	@Autowired
	private Assets assets;
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
		if (monster == null) {
			monster = assets.monsterMap.get(monsterName);
		}
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
