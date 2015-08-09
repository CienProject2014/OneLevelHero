package com.mygdx.model.unit;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.model.item.Item;

public class Monster extends Unit implements Fightable {
	public Monster() {
	}

	Monster(Status status) {
		this.status = status;
	}

	private String description;
	private ArrayList<Item> dropItems;
	private MonsterEnum.SizeType sizeType;
	private MonsterEnum.ElementType elementType;

	/* For Json Work */
	private ArrayList<String> dropItemList;

	public MonsterEnum.SizeType getSizeType() {
		return sizeType;
	}

	public void setSizeType(MonsterEnum.SizeType type) {
		this.sizeType = type;
	}

	public MonsterEnum.ElementType getElementType() {
		return elementType;
	}

	public void setElementType(MonsterEnum.ElementType elementType) {
		this.elementType = elementType;
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
		Gdx.app.log("Monster", this.getName() + "이(가) " + defender.getName()
				+ "을(를) 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit defender, String skillName) {
		Gdx.app.log("Monster", this.getName() + "이(가) " + skillName
				+ "을(를) 사용하였습니다!");
	}
	public ArrayList<String> getDropItemList() {
		return dropItemList;
	}

	public void setDropItemList(ArrayList<String> dropItemList) {
		this.dropItemList = dropItemList;
	}

	public ArrayList<Item> getDropItems() {
		return dropItems;
	}

	public void setDropItems(ArrayList<Item> dropItems) {
		this.dropItems = dropItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
