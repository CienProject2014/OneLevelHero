package com.mygdx.unitStrategy;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.SkillAssets;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class HeroAttackStrategy implements AttackStrategy {
	@Autowired
	private SkillAssets sillAssets;

	@Override
	public void attack(Unit attackHero, Unit defender, int[][] hitArea) {
		Monster monster = (Monster) defender;

		float factor = 1.0f;
		int attackDmg = attackHero.getStatus().getAttack();
		int defenseValue = defender.getStatus().getDefense();
		int defenderHp = defender.getStatus().getHp();

		int realDmg = 0;

		for (int i = 0; i < hitArea.length; i++) {
			for (int j = 0; j < hitArea[i].length; j++) {
				if (hitArea[i][j] == 1) {
					int tmpDmg = (int) (attackDmg * factor - defenseValue);
					if (tmpDmg < 1) {
						realDmg += 1 * (monster.getHitArea()[i][j] / 100.0f);
					} else {
						realDmg += tmpDmg * (monster.getHitArea()[i][j] / 100.0f);
					}
				}
			}
		}

		if (defenderHp - realDmg > 0) {
			defender.getStatus().setHp(defenderHp - realDmg);
		} else {
			defender.getStatus().setHp(0);
		}

		Gdx.app.log("Hero", attackHero.getName() + "이(가) " + defender.getName() + "을(를) 공격하였습니다!");
	}

	@Override
	public void skillAttack(Unit attackHero, Unit defender, String skillName) {
		Gdx.app.log("Hero", attackHero.getName() + "이(가) " + defender.getName() + "에게 " + skillName + "을(를) 사용하였습니다!");
		int defenderHp = defender.getStatus().getHp();

		Skill s = sillAssets.getSkill(skillName);
		switch (s.getSkillType()) {
		case MAGIC:
			float magicDamage = attackHero.getStatus().getMagicAttack() * s.getDamage() / 100f;
			int magicDefense = defender.getStatus().getMagicDefense();
			int realMagicDamage = (int) (magicDamage - magicDefense);
			if (realMagicDamage < 0) {
				realMagicDamage = 1;
			}

			if (defenderHp - realMagicDamage > 0) {
				defender.getStatus().setHp(defenderHp - realMagicDamage);
			} else {
				defender.getStatus().setHp(0);
			}
			break;
		case TECH:
			float skillDamage = attackHero.getStatus().getAttack() * s.getDamage() / 100f;
			int skillDefense = defender.getStatus().getDefense();
			int realSkillDamage = (int) (skillDamage - skillDefense);
			if (realSkillDamage < 0) {
				realSkillDamage = 1;
			}
			if (defenderHp - realSkillDamage > 0) {
				defender.getStatus().setHp(defenderHp - realSkillDamage);
			} else {
				defender.getStatus().setHp(0);
			}
			break;
		default:
			break;

		}

	}
}
