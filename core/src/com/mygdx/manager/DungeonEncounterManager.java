package com.mygdx.manager;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.unit.Monster;

public class DungeonEncounterManager {
	@Autowired
	private MonsterPickManager monsterManager;
	@Autowired
	private BattleManager battleManager;

	private Random random = new Random();

	public void encountEnemy() {
		// FIXME
		Gdx.app.log("ectEnemy", "chk");
		battleManager.setBeforePosition(PositionEnum.DUNGEON);
		Monster selectedMonster = monsterManager.createMonster();
		battleManager.startBattle(selectedMonster);

	}
	// FIXME 전투 랜덤으로 발생, 기획에 맞게 바꿀 것
	public boolean isBattleOccured() {
		return random.nextBoolean();
	}

	public void eliteAct(String eliteMonster) {
		Monster selectedMonster = monsterManager.createSpecificMoster(eliteMonster);
		battleManager.startBattle(selectedMonster);
	}

	public void act() {
		if (isBattleOccured()) {
			encountEnemy();
		}
	}
}
