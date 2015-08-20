package com.mygdx.unitStrategy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.SkillEffectEnum;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class HeroBattleStrategy implements BattleStrategy {
	private final String TAG = "HeroAttackStrategy";

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

		Gdx.app.log(TAG, attackHero.getName() + "이(가) " + defender.getName() + "을(를) 공격하였습니다!");
	}

	@Override
	public void skill(Unit skillUser, ArrayList<Unit> targetList, Skill skill) {
		if (skill == null) {
			Gdx.app.log(TAG, "잘못된 스킬: null 입력");
			return;
		}

		Gdx.app.log("Hero", skillUser.getName() + "이(가) " + targetList.get(0).getName() + "에게 " + skill.getName()
				+ "을(를) 사용하였습니다!");

		// 각 타겟에 대해 SkillEffectType에 따라 사용
		for (Unit target : targetList) {
			if (skill.getSkillEffectType() == SkillEffectEnum.MULTI_EFFECT) {
				String[] effectList = skill.getEffectNameList();

				for (String effect : effectList) {
					SkillEffectEnum effectEnum = SkillEffectEnum.valueOf(effect);
					if (effectEnum != null) {
						skill.setSkillEffectType(effectEnum);
						applyEffect(skillUser, target, skill);
					} else {
						Gdx.app.log(TAG, "잘못된 스킬 타입: " + effect + " 타입이 존재하지 않습니다.");
					}
				}
			} else {
				applyEffect(skillUser, target, skill);
			}
		}
	}

	private void applyEffect(Unit attackHero, Unit defender, Skill skill) {
		switch (skill.getSkillEffectType()) {
		case ADD_CONDITIONAL_STATE:
			break;
		case ADD_STATE:
			break;
		case ATTACK:
			basicAttack(attackHero, defender, skill.getSkillFactor(), skill.getMagicFactor());
			break;
		case CHANGE_GAUGE:
			changeGauge(attackHero, defender, skill.getMagicFactor());
			break;
		case CONDITIONAL_ATTACK:
			conditionalAttack(attackHero, defender, skill.getOneRegex(), skill.getSkillFactor(),
					skill.getMagicFactor());
			break;
		case DUPLICATED_ATTACK:
			duplicatedAttack(attackHero, defender, skill.getDuplicateNumber(), skill.getSkillFactor(),
					skill.getMagicFactor());
			break;
		case HEAL:
			heal(attackHero, defender, skill.getMagicFactor());
			break;
		case REMOVE_STATE:
			break;
		default:
			break;

		}
	}

	private void basicAttack(Unit attackHero, Unit defender, float skillFactor, float magicFactor) {
		int defenderHp = defender.getStatus().getHp();
		float skillDamage = attackHero.getStatus().getAttack() * skillFactor / 100f;
		int skillDefense = defender.getStatus().getDefense();
		int realSkillDamage = (int) (skillDamage - skillDefense);
		if (realSkillDamage < 0) {
			realSkillDamage = 1;
		}

		float magicDamage = attackHero.getStatus().getMagicAttack() * magicFactor / 100f;
		int magicDefense = defender.getStatus().getMagicDefense();
		int realMagicDamage = (int) (magicDamage - magicDefense);
		if (realMagicDamage < 0) {
			realMagicDamage = 1;
		}

		float totalDamage = realSkillDamage + realMagicDamage;

		if (defenderHp - totalDamage > 0) {
			defender.getStatus().setHp((int) (defenderHp - totalDamage));
		} else {
			defender.getStatus().setHp(0);
		}
	}

	private void duplicatedAttack(Unit attackHero, Unit defender, int duplicateNumber, float skillFactor,
			float magicFactor) {
		if (duplicateNumber <= 1) {
			Gdx.app.log(TAG, "잘못된 스킬 설계: duplicatedAttack의 횟수가 0 또는 1 입니다.");
		}

		for (int i = 0; i < duplicateNumber; i++) {
			basicAttack(attackHero, defender, skillFactor, magicFactor);
		}
	}

	private void conditionalAttack(Unit attackHero, Unit defender, String oneRegex, float skillFactor,
			float magicFactor) {
		boolean condition = true;

		// TODO: oneRegex를 분석해서 조건을 충족시키는지 확인하는 로직 필요

		if (condition) {
			basicAttack(attackHero, defender, skillFactor, magicFactor);
		}
	}

	private void addState(Unit attackHero, Unit defender, Skill skill) {

	}

	private void removeState(Unit attackHero, Unit defender, Skill skill) {

	}

	private void addConditionalState(Unit attackHero, Unit defender, Skill skill) {

	}

	private void changeGauge(Unit attackHero, Unit defender, float magicFactor) {
		// TODO: 현재는 magicFactor만큼 깎도록 구현
		int gauge = defender.getGauge();
		float gaugeDecrement = gauge * (magicFactor / 100f);

		if (gauge - gaugeDecrement >= 0) {
			defender.setGauge((int) (gauge - gaugeDecrement));
		}
	}

	private void heal(Unit attackHero, Unit defender, float magicFactor) {
		int healAmount = (int) (defender.getStatus().getMaxHp() * (magicFactor / 100f));
		int hp = defender.getStatus().getHp();
		int maxHp = defender.getStatus().getMaxHp();

		if (hp + healAmount >= maxHp) {
			defender.getStatus().setHp(maxHp);
		} else {
			defender.getStatus().setHp(hp + healAmount);
		}
	}

}
