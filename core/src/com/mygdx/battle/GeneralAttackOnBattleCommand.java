package com.mygdx.battle;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Unit;

public class GeneralAttackOnBattleCommand implements BattleCommand {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AnimationManager animationManager;

	@Override
	public void doCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		attackUnit.attack(defendUnit, hitArea);
		battleManager.getBattleUi().getBattleStage().getCameraManager().shaking();

		if (attackUnit instanceof Hero) {
			int centerX = getCenterToHitAreaX(hitArea);
			int centerY = getCenterToHitAreaY(hitArea);
			animationManager.registerAnimation("empty hit", centerX, 4 - centerY);
		} else {
			animationManager.registerAnimation((Hero) defendUnit, "empty hit");
		}

		if (animationManager.isEmptyAnimation()) {
			battleManager.checkTurnEnd();
		}
	}

	private int getCenterToHitAreaY(int[][] hit) {
		if (hit != null) {
			int ret = 0;
			int count = 0;
			for (int i = 0; i < hit.length; i++) {
				for (int j = 0; j < hit[i].length; j++) {
					if (hit[i][j] != 0) {
						ret += i;
						count++;
					}
				}
			}
			if (count == 0) {
				return 0;
			}
			return (int) (ret / count);
		}
		return 0;
	}

	private int getCenterToHitAreaX(int[][] hit) {
		if (hit != null) {
			int ret = 0;
			int count = 0;
			for (int i = 0; i < hit.length; i++) {
				for (int j = 0; j < hit[i].length; j++) {
					if (hit[i][j] != 0) {
						ret += j;
						count++;
					}
				}
			}
			if (count == 0) {
				return 0;
			}
			return (int) (ret / count);
		}
		return 0;
	}

}
