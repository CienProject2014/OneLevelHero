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
		int attackDmg = (int) attackMonster.getStatus().getAttack();
		int defenseValue = (int) defender.getStatus().getDefense();
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

	@Override
	public void runBuffEffect(Unit defender) {
		applyBuff(defender);
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
			defender.getBuffList().remove(removableBuff);
		}

	}

	private void applyAllBuffEffect(Unit defender, Buff buff) {
		for (String buffEffect : buff.getBuffEffectList()) {
			switch (BuffEffectEnum.findBuffEffectEnum(buffEffect)) {
			case BLOCK_ACTION:
				blockAction(defender);
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
			default:
				break;
			}
		}
	}

	private void decreaseDefense(Unit defender, Buff buff) {
		Hero hero = (Hero) defender;
		defender.getStatus().setDefense(hero.getInventory().getAllDefense());
	}

	private void increaseDefense(Unit defender, Buff buff) {
		defender.getStatus()
				.setDefense(defender.getStatus().getDefense() / 100 * (buff.getIncreaseDefensePercent() + 100));
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
}
