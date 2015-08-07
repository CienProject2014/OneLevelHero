package com.mygdx.manager;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.model.unit.Monster;

public class EncounterManager {
	@Autowired
	private MonsterPickManager monsterManager;
	@Autowired
	private BattleManager battleManager;

	private Random random = new Random();

	public void encountEnemy() {
		// 몬스터 소환!
		Monster selectedMonster = monsterManager.createMonster();
		battleManager.startBattle(selectedMonster);

	}

	// FIXME 전투 랜덤으로 발생, 기획에 맞게 바꿀 것
	public boolean isBattleOccured() {
		return random.nextBoolean();
	}
}
