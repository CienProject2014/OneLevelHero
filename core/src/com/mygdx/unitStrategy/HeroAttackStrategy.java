package com.mygdx.unitStrategy;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.unit.Unit;

public class HeroAttackStrategy implements AttackStrategy {
	@Override
	public void attack(Unit attackHero, Unit defender) {
		int attackDmg = attackHero.getStatus().getAttack();
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
		Gdx.app.log("Hero", attackHero.getName() + "이(가) " + defender.getName()
				+ "을(를) 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit attackHero, Unit defender, String skillName) {
		Gdx.app.log("Hero", attackHero.getName() + "이(가) " + defender.getName()
				+ "에게 " + skillName + "을(를) 사용하였습니다!");
	}
}
