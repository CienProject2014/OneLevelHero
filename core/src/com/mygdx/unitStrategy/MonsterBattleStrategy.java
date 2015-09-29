package com.mygdx.unitStrategy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.SkillAssets;
import com.mygdx.enums.BuffEffectEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Unit;

public class MonsterBattleStrategy implements BattleStrategy {

	@Autowired
	private transient SkillAssets skillAssets;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private PartyManager partyManager;

	@Override
	public void attack(Unit attackMonster, Unit defender, int[][] hitArea) {
		Buff overload = skillAssets.getBuff("overload");
		int attackDmg = (int) attackMonster.getRealStatus().getAttack();
		int defenseValue = (int) defender.getRealStatus().getDefense();
		int defenderHp = defender.getStatus().getHp();
		int realDmg = attackDmg - defenseValue;
		if (realDmg < 1) {
			realDmg = 1;
		}
		if (defenderHp - realDmg > 0) {
			defender.getStatus().setHp(defenderHp - realDmg);
		} else {
			defender.getStatus().setHp(0);
		}
		if (defender.getStatus().getCasting() > 0) {
			if (defender.getStatus().getCasting() == 1) {
				defender.getBuffList().add(overload);
			} else {
				defender.getBuffList().remove(overload);
				defender.getBuffList().add(overload);
			}
			defender.setOverload(defender.getOverload() + 1);
			if (defender.getOverload() == 5) {
				defender.getStatus().setCasting(0);
				for (Hero hero : partyManager.getBattleMemberList()) {
					hero.getStatus().setHp(hero.getStatus().getHp() - defender.getStatus().getCasting() * 10);
				}
				defender.setOverload(0);
				defender.getBuffList().remove(overload);

			}
		}
		Gdx.app.log("Monster", attackMonster.getName() + "이(가) " + defender.getName() + "을(를) 공격하였습니다!");
		battleManager.setText(
				attackMonster.getName() + "이(가) " + defender.getName() + "을(를) 공격하였다! " + "데미지 " + realDmg + "를 입혔다!");
	}

	@Override
	public void skill(Unit attackMonster, ArrayList<Unit> targetList, Skill skill) {
		Gdx.app.log("Monster", attackMonster.getName() + "이(가) " + skill.getName() + "을(를) 사용하였습니다!");
	}

	private void applyBuff(Unit defender) {

		ArrayList<Buff> cancelList = new ArrayList<Buff>();

		int deltaTime = timeManager.getPreTime();

		for (Buff buff : defender.getBuffList()) {
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
