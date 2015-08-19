package com.mygdx.unitStrategy;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.unit.Unit;

public class MonsterAttackStrategy implements AttackStrategy {
	@Override
	public void attack(Unit attackMonster, Unit defender, int[][] hitArea) {
		int attackDmg = attackMonster.getStatus().getAttack();
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
		Gdx.app.log("Monster", attackMonster.getName() + "이(가) " + defender.getName() + "을(를) 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit attackMonster, Unit defender, String skillName) {
		Gdx.app.log("Monster", attackMonster.getName() + "이(가) " + skillName + "을(를) 사용하였습니다!");
	}
}
