package com.mygdx.battle;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.SkillAssets;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.model.unit.Unit;

public class UseSkillOnBattleCommand implements BattleCommand {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private SkillAssets skillAssets;
	@Autowired
	private AnimationManager animationManager;

	@Override
	public void doCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		String skillName = battleManager.getCurrentSelectedSkill().getSkillPath();
		Skill skill = skillAssets.getSkill(skillName);
		ArrayList<Unit> targetList = battleManager.getSkillTargetUnitList(skill.getSkillTargetType(), attackUnit,
				defendUnit);
		attackUnit.getStatus().setCasting(attackUnit.getStatus().getCasting() - skill.getCostCasting());
		battleManager.checkCastingValue(attackUnit);
		attackUnit.useSkill(targetList, skill);
		animationManager.registerAnimation(attackUnit, defendUnit, skillName);

		soundManager.setSoundByPathAndPlay("skill_" + battleManager.getCurrentSelectedSkill().getSkillPath());
		battleManager.setUsingSkill(false);
		if (animationManager.isEmptyAnimation()) {
			battleManager.checkTurnEnd();
		}
	}
}
