package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.MonsterEnum;

public class Monster extends Unit implements Fightable {
	public Monster() {
	}

	Monster(Status status) {
		this.status = status;
	}

	private MonsterEnum.SizeType sizeType;
	private MonsterEnum.ElementType elementType;

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
		Gdx.app.log("Monster", this.getName() + "가 " + defender.getName()
				+ "를 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit defender, String skillName) {
		Gdx.app.log("Monster", this.getName() + "가 " + skillName + "를 사용하였습니다!");
	}
}
