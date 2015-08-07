package com.mygdx.model;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class Hero extends Unit implements Fightable {
	private Equipment equipment;
	private Map<String, Item> items;

	/* For Json Work */
	private ArrayList<String> itemList;

	public ArrayList<String> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<String> itemList) {
		this.itemList = itemList;
	}

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public void attack(Unit defender) {
		int attackDmg = this.getStatus().getAttack();
		int defenseValue = defender.getStatus().getDefense();
		int defenderHp = defender.getStatus().getHp();
		int realDmg = attackDmg - defenseValue;
		if (realDmg < 0) {
			realDmg = 1;
		}
		if (defenderHp - realDmg > 0) {
			defender.getStatus().setHp(defenderHp - realDmg);
		} else {
			defender.getStatus().setHp(0);
		}
		Gdx.app.log("Monster", this.getName() + "가 " + defender.getName()
				+ "를 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit defender, String skillName) {
		Gdx.app.log("Hero", this.getName() + "가 " + defender.getName() + "에게 "
				+ skillName + "를 사용하였습니다!");

	}
}