package com.mygdx.manager;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

@Component
public class EncounterManager {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MonsterManager monsterManager;
	private Random random = new Random();

	public void encountEnemy() {
		// 몬스터 소환!
		monsterManager.createMonster();
		screenFactory.show(ScreenEnum.ENCOUNTER);
	}

	// FIXME 전투 랜덤으로 발생, 기획에 맞게 바꿀 것
	public boolean isBattleOccured() {
		return true;
	}
}
