package com.mygdx.unitStrategy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Unit;

public class MonsterBattleStrategy implements BattleStrategy {
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
	public void skill(Unit attackMonster, ArrayList<Unit> targetList, Skill skill) {
		Gdx.app.log("Monster", attackMonster.getName() + "이(가) " + skill.getName() + "을(를) 사용하였습니다!");
	}
}
