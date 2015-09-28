package com.mygdx.manager;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.CurrentInfo;
import com.mygdx.model.unit.Monster;

public class EncounterManager {
	@Autowired
	private MonsterPickManager monsterManager;
	@Autowired
	private BattleManager battleManager;

	private Random random = new Random();
	// FIXME 전투 랜덤으로 발생, 기획에 맞게 바꿀 것

	public boolean isBattleOccured() {
		if (!CurrentInfo.isAdminMode) {
			return random.nextBoolean();
		} else {
			return false;
		}
	}

	public void encountEnemy(ArrayList<String> monsterList) {
		if (isBattleOccured() && monsterList.size() != 0) {
			Monster selectedMonster = monsterManager.createMonster(monsterList);
			battleManager.startBattle(selectedMonster);
		}
	}

	public void encountEliteMonster(String eliteMonsterName) {
		if (!CurrentInfo.isAdminMode) {
			Monster selectedMonster = monsterManager.createMonsterByName(eliteMonsterName);
			battleManager.startBattle(selectedMonster);
		}
	}

	public void encountBossMonster(String bossMonsterName) {
		Monster selectedMonster = monsterManager.createMonsterByName(bossMonsterName);
		battleManager.startBattle(selectedMonster);
	}
}
