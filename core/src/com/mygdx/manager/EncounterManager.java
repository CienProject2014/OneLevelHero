package com.mygdx.manager;

import java.util.Random;

import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;

public class EncounterManager {
	private static Random random = new Random();

	public static void encountEnemy() {
		//몬스터 소환!
		MonsterManager.createMonster();
		new ScreenController(ScreenEnum.ENCOUNT);
	}

	//FIXME 전투 랜덤으로 발생, 기획에 맞게 바꿀 것
	public static boolean isBattleOccured() {
		return random.nextBoolean();
	}

}
