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
	private transient SkillAssets skillAssets;
	@Autowired
	private transient TimeManager timeManager;
	@Autowired
	private transient PartyManager partyManager;
	@Autowired
	private transient BattleManager battleManager;

	@Override
	public void attack(Unit attacker, Unit defender, int[][] hitArea) {
		Monster monster = (Monster) defender;

		float factor = 1.0f;

		int attackDmg = (int) attacker.getRealStatus().getAttack();
		int defenseValue = (int) defender.getRealStatus().getDefense();

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

		Gdx.app.log(TAG,
				attacker.getName() + "이(가) " + defender.getName() + "을(를) 공격하였다! " + "데미지 " + realDmg + "를 입혔다!");
		battleManager.setText(
				attacker.getName() + "이(가) " + defender.getName() + "을(를) 공격하였다! " + "데미지 " + realDmg + "를 입혔다!");
	}

	@Override
	public void skill(Unit skillUser, ArrayList<Unit> targetList, Skill skill) {
		if (skill == null) {
			Gdx.app.log(TAG, "잘못된 스킬: null 입력");
			return;
		}

		battleManager.setText(
				skillUser.getName() + "이(가) " + targetList.get(0).getName() + "에게 " + skill.getName() + "을(를) 사용하였다!");
		// 각 타겟에 대해 SkillEffectType에 따라 사용

		for (Unit target : targetList) {
			if (skill.getSkillEffectType().equals(SkillEffectEnum.MULTI_EFFECT.toString())) {
				String[] effectList = skill.getEffectNameList();

				for (String effect : effectList) {
					SkillEffectEnum effectEnum = SkillEffectEnum.findSkillEffectEnum(effect);
					if (effectEnum != null) {
						skill.setSkillCheckType(effectEnum.toString());
						applyEffect(skillUser, target, skill);
					} else {
						basicAttack(skillUser, target, skill.getSkillFactor(), skill.getMagicFactor());
						Gdx.app.log(TAG, "잘못된 스킬 타입: " + effect + " 타입이 존재하지 않습니다.");
					}
				}
			} else {
				skill.setSkillCheckType(skill.getSkillEffectType().toString());
				applyEffect(skillUser, target, skill);
			}
		}
	}

	private void applyEffect(Unit attacker, Unit defender, Skill skill) {
		switch (SkillEffectEnum.findSkillEffectEnum(skill.getSkillCheckType())) {
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

		Monster monster = (Monster) defender;
		int defenderHp = defender.getStatus().getHp();
		int skillDamage = (int) attacker.getRealStatus().getAttack();
		int skillDefense = (int) defender.getRealStatus().getDefense();
		float tmpDmg = ((int) (skillDamage - skillDefense) * skillFactor / 100f);
		float totalDamage;
		float realSkillDamage = 0;

		int magicDamage = (int) attacker.getRealStatus().getMagicAttack();
		int magicDefense = (int) defender.getRealStatus().getMagicDefense();
		float realMagicDamage = (magicDamage - magicDefense) * magicFactor / 100f;
		if (realMagicDamage < 1) {
			realMagicDamage = 1;
		}
		if (battleManager.isShowGrid()) {
			int[][] hitArea = battleManager.getNowGridHitbox().getPreviousHitArea();
			for (int i = 0; i < hitArea.length; i++) {
				for (int j = 0; j < hitArea[i].length; j++) {
					if (hitArea[i][j] == 1) {
						if (tmpDmg < 1) {
							realSkillDamage += 1 * (monster.getHitArea()[i][j] / 100.0f);
						} else {
							realSkillDamage += tmpDmg * (monster.getHitArea()[i][j] / 100.0f);
						}
					}
				}
			}
		}

		if (realSkillDamage < 1) {
			realSkillDamage = 1;
		}
		if (battleManager.getCurrentSelectedSkill().getBuffName().equals("solid")) {
			// 견고화의 경우 대미지에 자신의 방어력을 더한 대미지를 준다.
			totalDamage = realSkillDamage + realMagicDamage + attacker.getRealStatus().getDefense();
		} else if (battleManager.getCurrentSelectedSkill().getSkillPath().equals("whirlwind")) {
			// 휠윈드의 경우 상대방이 가지고 있는 버프 사이즈만큼 곱한 데미지를 더한다.
			totalDamage = realSkillDamage + realMagicDamage
					+ attacker.getRealStatus().getAttack() * defender.getBuffList().size();
		} else {
			totalDamage = realSkillDamage + realMagicDamage;
		}

		if (defenderHp - totalDamage > 0) {
			defender.getStatus().setHp((int) (defenderHp - totalDamage));
		} else {
			defender.getStatus().setHp(0);
		}
		battleManager.setText(attacker.getName() + "이(가) " + defender.getName() + "에게 "
				+ battleManager.getCurrentSelectedSkill().getName() + "을(를) 사용하였다!" + "데미지 " + (int) totalDamage
				+ "를 입혔다!");

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

		if (buff.getBuffPath().equals("multi")) {
			for (String buffList : skill.getBuffNameList()) {
				Buff buffs = skillAssets.getBuff(buffList);
				buffs.setAttacker(attacker);
				if (defender.getBuffList().contains(buffs)) {
					for (String buffEffect : buffs.getBuffEffectList()) {
						if (BuffEffectEnum.findBuffEffectEnum(buffEffect) == BuffEffectEnum.BLOCK_ACTION) {
							Gdx.app.log("HeroBattleStrategy", "스턴은 갱신되지 않습니다.");
						} else {
							defender.getBuffList().remove(buffs);
							defender.getBuffList().add(buffs);
							applyBuff(defender);
						}
					}
				} else {
					if (skill.getSkillPath().equals("strong_breeze")) {
						// strong_breeze일때는 출혈에 걸린 상태에만 스턴을 건다.
						if (defender.getBuffList().contains(bleeding)) {
							defender.getBuffList().add(buffs);
							applyBuff(defender);
						}
					} else {
						defender.getBuffList().add(buffs);
						applyBuff(defender);
					}
				}
			}
		} else {
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
			reset(defender, removableBuff);
			defender.getBuffList().remove(removableBuff);
		}

	}

	private void reset(Unit defender, Buff buff) {
		battleManager.setEndBuff(true);
		for (String buffEffect : buff.getBuffEffectList()) {
			switch (BuffEffectEnum.findBuffEffectEnum(buffEffect)) {
			case BLOCK_ACTION:
				blockAction(defender);
				break;
			case INCREASE_AGGRO:
				increaseAggro(defender);
				break;
			case DECREASE_ATTACK:
				decreaseAttack(defender, buff);
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
			case OVERLOAD:
				overload(defender);
				break;
			case OVERWORK:
				overwork(defender);
				break;
			case SHOCK:
				shock(defender);
				break;
			case WEAK:
				weak(defender);
				break;
			case STINK:
				stink(defender);
				break;
			case DECLINE:
				decline(defender);
				break;
			case CHARM:
				charm(defender);
				break;
			case INCREASE_FIRE_RESISTANCE:
				increaseFireResistance(defender, buff);
				break;
			case INCREASE_WATER_RESISTANCE:
				increaseWaterResistance(defender, buff);
				break;
			case INCREASE_ELECTRIC_RESISTANCE:
				increaseElectricResistance(defender, buff);
				break;
			case BLESS:
				bless(defender);
				break;
			case DEFAULT:
			default:
				break;
			}
		}
	}

	private void applyAllBuffEffect(Unit defender, Buff buff) {

		battleManager.setEndBuff(false);
		for (String buffEffect : buff.getBuffEffectList()) {
			switch (BuffEffectEnum.findBuffEffectEnum(buffEffect)) {
			case BLOCK_ACTION:
				blockAction(defender);
				break;
			case INCREASE_AGGRO:
				increaseAggro(defender);
				break;
			case DECREASE_ATTACK:
				decreaseAttack(defender, buff);
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
			case OVERLOAD:
				overload(defender);
				break;
			case OVERWORK:
				overwork(defender);
				break;
			case SHOCK:
				shock(defender);
				break;
			case WEAK:
				weak(defender);
				break;
			case STINK:
				stink(defender);
				break;
			case DECLINE:
				decline(defender);
				break;
			case CHARM:
				charm(defender);
				break;
			case INCREASE_FIRE_RESISTANCE:
				increaseFireResistance(defender, buff);
				break;
			case INCREASE_WATER_RESISTANCE:
				increaseWaterResistance(defender, buff);
				break;
			case INCREASE_ELECTRIC_RESISTANCE:
				increaseElectricResistance(defender, buff);
				break;
			case BLESS:
				bless(defender);
				break;
			case DEFAULT:
			default:
				break;
			}
		}
	}

	private void decreaseAttack(Unit defender, Buff buff) {
		float preAttack = defender.getStatus().getAttack() * buff.getDecreaseAttackPercent() / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack());
		} else {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack() - preAttack);
		}
	}

	private void bless(Unit defender) {
		float preMagicDefense = defender.getStatus().getAttack() * 15 / 100;
		float preDefense = defender.getStatus().getDefense() * 15 / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setMagicDefense(defender.getStatus().getMagicDefense());
			defender.getRealStatus().setDefense(defender.getStatus().getDefense());
		} else {
			defender.getRealStatus().setMagicDefense(defender.getStatus().getMagicDefense() + preMagicDefense);
			defender.getRealStatus().setDefense(defender.getStatus().getDefense() + preDefense);
		}
	}

	private void increaseElectricResistance(Unit defender, Buff buff) {
		int resistance = buff.getIncreaseElectricResistance();
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setElectricResistance(defender.getStatus().getElectricResistance());
		} else {
			defender.getRealStatus().setElectricResistance(defender.getStatus().getElectricResistance() + resistance);
		}
	}

	private void increaseWaterResistance(Unit defender, Buff buff) {
		int resistance = buff.getIncreaseWaterResistance();
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setWaterResistance(defender.getStatus().getWaterResistance());
		} else {
			defender.getRealStatus().setWaterResistance(defender.getStatus().getWaterResistance() + resistance);
		}
	}

	private void increaseFireResistance(Unit defender, Buff buff) {
		int resistance = buff.getIncreaseFireResistance();
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setFireResistance(defender.getStatus().getFireResistance());
		} else {
			defender.getRealStatus().setFireResistance(defender.getStatus().getFireResistance() + resistance);
		}
	}

	private void charm(Unit defender) {
		float preAttack = defender.getStatus().getAttack() * 30 / 100;
		float preMagicAttack = defender.getStatus().getMagicAttack() * 30 / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack());
			defender.getRealStatus().setMagicAttack(defender.getStatus().getMagicAttack());
		} else {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack() - preAttack);
			defender.getRealStatus().setMagicAttack(defender.getStatus().getMagicAttack() - preMagicAttack);
		}
	}

	private void decline(Unit defender) {
		float preMagicDefense = defender.getStatus().getMagicDefense() * 10 / 100;
		float preDefense = defender.getStatus().getDefense() * 10 / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setMagicDefense(defender.getStatus().getMagicDefense());
			defender.getRealStatus().setDefense(defender.getStatus().getDefense());
		} else {
			defender.getRealStatus().setMagicDefense(defender.getStatus().getMagicDefense() - preMagicDefense);
			defender.getRealStatus().setDefense(defender.getStatus().getDefense() - preDefense);

		}
	}

	private void stink(Unit defender) {
		float preAttack = defender.getStatus().getAttack() * 20 / 100;
		float preDefense = defender.getStatus().getDefense() * 20 / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack());
			defender.getRealStatus().setDefense(defender.getStatus().getDefense());
		} else {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack() - preAttack);
			defender.getRealStatus().setDefense(defender.getStatus().getDefense() - preDefense);

		}

	}

	private void weak(Unit defender) {
		float preAttack = defender.getStatus().getAttack() * 10 / 100;
		float preMagicAttack = defender.getStatus().getMagicAttack() * 10 / 100;
		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack());
			defender.getRealStatus().setMagicAttack(defender.getStatus().getMagicAttack());
		} else {
			defender.getRealStatus().setAttack(defender.getStatus().getAttack() - preAttack);
			defender.getRealStatus().setMagicAttack(defender.getStatus().getMagicAttack() - preMagicAttack);
		}
	}

	private void shock(Unit defender) {
		// TODO Auto-generated method stub

	}

	private void overwork(Unit defender) {
		for (Hero hero : partyManager.getBattleMemberList()) {
			float preAttack = hero.getStatus().getAttack() * 20 / 100;
			float preDefense = hero.getStatus().getDefense() * 20 / 100;

			if (battleManager.isEndBuff()) {
				hero.getRealStatus().setAttack(hero.getStatus().getAttack());
				hero.getRealStatus().setDefense(hero.getStatus().getDefense());
			} else {
				hero.getRealStatus().setAttack(hero.getStatus().getAttack() - preAttack);
				hero.getRealStatus().setDefense(hero.getStatus().getDefense() - preDefense);
			}
		}
	}

	private void overload(Unit defender) {
	}

	private void flyAction(Unit defender) {

		if (battleManager.isEndBuff()) {
			defender.setRealAggro(100);
		} else {
			defender.setRealAggro(0);
		}
	}

	private void decreaseSpeed(Unit defender, Buff buff) {
		int preSpeed = defender.getStatus().getSpeed() - 50;

		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setSpeed(defender.getStatus().getSpeed());
		} else {
			defender.getRealStatus().setSpeed(preSpeed);
		}
	}

	private void increaseAggro(Unit defender) {
		int preAggro = defender.getAggro() + 900;

		if (battleManager.isEndBuff()) {
			defender.setRealAggro(100);
		} else {
			defender.setRealAggro(preAggro);
		}
	}

	private void decreaseDefense(Unit defender, Buff buff) {
		Hero hero = (Hero) defender;
		float preDefense = defender.getStatus().getDefense() - hero.getInventory().getAllDefense();

		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setDefense(defender.getStatus().getDefense());
		} else {
			defender.getRealStatus().setDefense(preDefense);
		}
	}

	private void increaseDefense(Unit defender, Buff buff) {
		float defense = defender.getStatus().getDefense() + buff.getIncreaseDefensePercent() / 100;

		if (battleManager.isEndBuff()) {
			defender.getRealStatus().setDefense(defender.getStatus().getDefense());
		} else {
			defender.getRealStatus().setDefense(defender.getStatus().getDefense() + defense);
		}
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
