package com.mygdx.model.unit;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class Hero extends Unit implements Fightable {
	private Inventory inventory;

	/* For Json Work */
	private HashMap<String, String> initialInventoryList;

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
		Gdx.app.log("Hero", this.getName() + "이(가) " + defender.getName()
				+ "을(를) 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit defender, String skillName) {
		Gdx.app.log("Hero", this.getName() + "이(가) " + defender.getName()
				+ "에게 " + skillName + "을(를) 사용하였습니다!");

	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public HashMap<String, String> getInitialInventoryList() {
		return initialInventoryList;
	}

	public void setInitialInventoryList(HashMap<String, String> initialInventoryList) {
		this.initialInventoryList = initialInventoryList;
	}

}