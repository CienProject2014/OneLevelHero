package com.mygdx.unitStrategy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.SkillAssets;
import com.mygdx.enums.BuffEffectEnum;
import com.mygdx.enums.SkillEffectEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class HeroBattleStrategy implements BattleStrategy {
	private final String TAG = "HeroBattleStrategy";

	@Autowired
	SkillAssets skillAssets;
	@Autowired
	TimeManager timeManager;
	@Autowired
	PartyManager partyManager;
	@Autowired
	BattleManager battleManager;

	@Override
	public void attack(Unit attacker, Unit defender, int[][] hitArea) {
		Monster monster = (Monster) defender;

		float factor = 1.0f;
		int attackDmg = (int) attacker.getStatus().getAttack();
		int defenseValue = (int) defender.getStatus().getDefense();
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

		if (realDmg < 1) {
			realDmg = 1;
		}

		if (defenderHp - realDmg > 0) {
			defender.getStatus().setHp(defenderHp - realDmg);
		} else {
			defender.getStatus().setHp(0);
		}

		Gdx.app.log(TAG, attacker.getName() + "이(가) " + defender.getName() + "을(를) 공격하였습니다!");
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
			if (skill.getSkillEffectType().equals(SkillEffectEnum.MULTI_EFFECT.toString())) {
				String[] effectList = skill.getEffectNameList();

				for (String effect : effectList) {
					SkillEffectEnum effectEnum = SkillEffectEnum.findSkillEffectEnum(effect);
					if (effectEnum != null) {
						skill.setSkillEffectType(effectEnum.toString());
						applyEffect(skillUser, target, skill);
					} else {
						basicAttack(skillUser, target, skill.getSkillFactor(), skill.getMagicFactor());
						Gdx.app.log(TAG, "잘못된 스킬 타입: " + effect + " 타입이 존재하지 않습니다.");
					}
				}
			} else {
				applyEffect(skillUser, target, skill);
			}
		}
	}

	private void applyEffect(Unit attacker, Unit defender, Skill skill) {
		switch (SkillEffectEnum.findSkillEffectEnum(skill.getSkillEffectType())) {
		case ADD_SELF_STATE:
			addSelfState(attacker, skill);
			break;
		case ADD_STATE:
			addState(attacker, defender, skill);
			break;
		case ATTACK:
			basicAttack(attacker, defender, skill.getSkillFactor(), skill.getMagicFactor());
			break;
		case CHANGE_GAUGE:
			changeGauge(attacker, defender, skill.getMagicFactor());
			break;
		case CONDITIONAL_ATTACK:
			conditionalAttack(attacker, defender, skill.getOneRegex(), skill.getSkillFactor(), skill.getMagicFactor());
			break;
		case DUPLICATED_ATTACK:
			duplicatedAttack(attacker, defender, skill.getDuplicateNumber(), skill.getSkillFactor(),
					skill.getMagicFactor());
			break;
		case HEAL:
			heal(attacker, defender, skill.getMagicFactor());
			break;
		case ALL_HEAL:
			allHeal(attacker, skill.getMagicFactor());
			break;
		case REMOVE_STATE:
			removeState(attacker, defender, skill);
			break;
		case CASTING:
			cast(attacker, skill);
			break;
		default:
			break;

		}
	}

	private void cast(Unit attacker, Skill skill) {
		attacker.getStatus().setCasting(attacker.getStatus().getCasting() + 1);
		if (attacker.getStatus().getCasting() > 5) {
			attacker.getStatus().setCasting(5);
		}
	}

	private void addSelfState(Unit attacker, Skill skill) {
		Buff buff = skillAssets.getBuff(skill.getBuffName());
		if (buff == null) {
			return;
		}
		buff.setAttacker(attacker);

		if (attacker.getBuffList().contains(buff)) {
			for (String buffEffect : buff.getBuffEffectList()) {
				if (BuffEffectEnum.findBuffEffectEnum(buffEffect) == BuffEffectEnum.BLOCK_ACTION) {
					Gdx.app.log("HeroBattleStrategy", "스턴은 갱신되지 않습니다.");
				} else {
					attacker.getBuffList().remove(buff);
					attacker.getBuffList().add(buff);
					applyBuff(attacker);
				}
			}
		} else {
			attacker.getBuffList().add(buff);
			applyBuff(attacker);
		}
	}

	private void allHeal(Unit attacker, float magicFactor) {
		for (int i = 0; i < partyManager.getBattleMemberList().size(); i++) {
			Unit defender = partyManager.getBattleMemberList().get(i);
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

	private void basicAttack(Unit attacker, Unit defender, float skillFactor, float magicFactor) {
		int defenderHp = defender.getStatus().getHp();
		int skillDamage = (int) attacker.getStatus().getAttack();
		int skillDefense = (int) defender.getStatus().getDefense();
		float totalDamage;
		float realSkillDamage = (int) (skillDamage - skillDefense) * skillFactor / 100f;
		if (realSkillDamage < 1) {
			realSkillDamage = 1;
		}

		int magicDamage = (int) attacker.getStatus().getMagicAttack();
		int magicDefense = (int) defender.getStatus().getMagicDefense();
		float realMagicDamage = (magicDamage - magicDefense) * magicFactor / 100f;
		if (realMagicDamage < 1) {
			realMagicDamage = 1;
		}

		if (battleManager.getCurrentSelectedSkill().getBuffName().equals("solid")) {
			totalDamage = realSkillDamage + realMagicDamage + attacker.getStatus().getDefense();
		} else if (battleManager.getCurrentSelectedSkill().getSkillPath().equals("whirlwind")) {
			totalDamage = realSkillDamage + realMagicDamage
					+ attacker.getStatus().getAttack() * defender.getBuffList().size();
		} else {
			totalDamage = realSkillDamage + realMagicDamage;
		}

		if (defenderHp - totalDamage > 0) {
			defender.getStatus().setHp((int) (defenderHp - totalDamage));
		} else {
			defender.getStatus().setHp(0);
		}

	}

	private void duplicatedAttack(Unit attacker, Unit defender, int duplicateNumber, float skillFactor,
			float magicFactor) {
		if (duplicateNumber <= 1) {
			Gdx.app.log(TAG, "잘못된 스킬 설계: duplicatedAttack의 횟수가 0 또는 1 입니다.");
			duplicateNumber = 2;
		}

		for (int i = 0; i < duplicateNumber; i++) {
			basicAttack(attacker, defender, skillFactor, magicFactor);
		}
	}

	private void conditionalAttack(Unit attacker, Unit defender, String oneRegex, float skillFactor,
			float magicFactor) {
		boolean condition = true;

		// TODO: oneRegex를 분석해서 조건을 충족시키는지 확인하는 로직 필요

		if (condition) {
			basicAttack(attacker, defender, skillFactor, magicFactor);
		}
	}

	private void changeGauge(Unit attacker, Unit defender, float magicFactor) {
		// TODO: 현재는 magicFactor만큼 깎도록 구현
		int gauge = defender.getGauge();
		float gaugeDecrement = gauge * (magicFactor / 100f);

		if (gauge - gaugeDecrement >= 0) {
			defender.setGauge((int) (gauge - gaugeDecrement));
		}
	}

	private void heal(Unit attacker, Unit defender, float magicFactor) {
		int healAmount = (int) (defender.getStatus().getMaxHp() * (magicFactor / 100f));
		int hp = defender.getStatus().getHp();
		int maxHp = defender.getStatus().getMaxHp();

		if (hp + healAmount >= maxHp) {
			defender.getStatus().setHp(maxHp);
		} else {
			defender.getStatus().setHp(hp + healAmount);
		}
	}

	private void addState(Unit attacker, Unit defender, Skill skill) {
		Buff buff = skillAssets.getBuff(skill.getBuffName());
		Buff bleeding = skillAssets.getBuff("bleeding");
		if (buff == null) {
			return;
		}
		buff.setAttacker(attacker);

		if (defender.getBuffList().contains(buff)) {
			for (String buffEffect : buff.getBuffEffectList()) {
				if (BuffEffectEnum.findBuffEffectEnum(buffEffect) == BuffEffectEnum.BLOCK_ACTION) {
					Gdx.app.log("HeroBattleStrategy", "스턴은 갱신되지 않습니다.");
				} else {
					defender.getBuffList().remove(buff);
					defender.getBuffList().add(buff);
					applyBuff(defender);
				}
			}
		} else {
			if (skill.getSkillPath().equals("strong_breeze")) {
				// strong_breeze일때는 출혈에 걸린 상태에만 스턴을 건다.
				if (defender.getBuffList().contains(bleeding)) {
					defender.getBuffList().add(buff);
					applyBuff(defender);
				}
			} else {
				defender.getBuffList().add(buff);
				applyBuff(defender);
			}
		}

	}

	private void removeState(Unit attacker, Unit defender, Skill skill) {
		Buff fly = skillAssets.getBuff("fly");
		if (skill.getBuffName().equals("all")) {
			defender.getBuffList().clear();
		} else if (skill.getBuffName().equals("fly")) {
			defender.getBuffList().remove(fly);
		}
	}

	private void applyBuff(Unit defender) {

		ArrayList<Buff> cancelList = new ArrayList<Buff>();

		int deltaTime = timeManager.getPreTime();

		for (Buff buff : defender.getBuffList()) {
			if (buff.getDuration() == -1) {
			} else {
				if (buff.getFlyingTime() >= buff.getDuration()) {
					cancelList.add(buff);
					continue;
				}

				buff.setPreFlyingTime(buff.getFlyingTime());

				if (buff.getFlyingTime() + deltaTime > buff.getDuration()) {
					float overVal = (buff.getFlyingTime() + deltaTime) - buff.getDuration();
					buff.addFlyingTime((int) (deltaTime - overVal));
				} else {
					buff.addFlyingTime(deltaTime);
				}
			}
			applyAllBuffEffect(defender, buff);

		}

		for (Buff removableBuff : cancelList) {
			removableBuff.setFlyingTime(0);
			reset();
			defender.getBuffList().remove(removableBuff);
		}

	}

	private void reset() {

	}

	private void applyAllBuffEffect(Unit defender, Buff buff) {
		for (String buffEffect : buff.getBuffEffectList()) {
			switch (BuffEffectEnum.findBuffEffectEnum(buffEffect)) {
			case BLOCK_ACTION:
				blockAction(defender);
				break;
			case INCREASE_AGGRO:
				increaseAggro(defender);
				break;
			case DECREASE_ATTACK:
				break;
			case DECREASE_HP_ITERATIVE:
				decreaseHpIterative(defender, buff);
				break;
			case DECREASE_MAGIC_ATTACK:
				break;
			case INCREASE_DEFENSE:
				increaseDefense(defender, buff);
				break;
			case DECREASE_DEFENSE:
				decreaseDefense(defender, buff);
				break;
			case DECREASE_SPEED:
				decreaseSpeed(defender, buff);
				break;
			case FLY_ACTION:
				flyAction(defender);
				break;
			default:
				break;
			}
		}
	}

	private void flyAction(Unit defender) {
		defender.setAggro(0);
	}

	private void decreaseSpeed(Unit defender, Buff buff) {
		defender.getStatus().setSpeed(defender.getStatus().getSpeed() - 50);
	}

	private void increaseAggro(Unit defender) {
		defender.setAggro(defender.getAggro() + 900);
	}

	private void decreaseDefense(Unit defender, Buff buff) {
		Hero hero = (Hero) defender;
		System.out.println("test");
		defender.getStatus().setDefense(defender.getStatus().getDefense() - hero.getInventory().getAllDefense());
	}

	private void increaseDefense(Unit defender, Buff buff) {
		float defense = defender.getStatus().getDefense() / 100 * (buff.getIncreaseDefensePercent() + 100);
		defender.getStatus().setDefense(defense);
	}

	private void blockAction(Unit defender) {
	}

	private void decreaseHpIterative(Unit defender, Buff buff) {
		float factor = ((float) buff.getFlyingTime() - buff.getPreFlyingTime()) / buff.getDecreaseHpPeriod();
		float attackerMagicDamage = buff.getAttacker().getStatus().getMagicAttack()
				* (buff.getDecreaseHpMagicPercent() / 100.0f);
		float attackerAttackDamage = buff.getAttacker().getStatus().getAttack()
				* (buff.getDecreaseHpAttackPercent() / 100.0f);
		float delta = factor * (attackerMagicDamage + attackerAttackDamage) + buff.getDecreaseHpOffset();
		defender.getStatus().setHp((int) (defender.getStatus().getHp() - delta));
	}

	@Override
	public void runBuffEffect(Unit defender) {
		applyBuff(defender);
	}

}
