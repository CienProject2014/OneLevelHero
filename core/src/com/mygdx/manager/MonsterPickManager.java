package com.mygdx.manager;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.UnitAssets;
import com.mygdx.model.unit.Monster;

/**
 * WorldMap에서 MovingInfoManager를 통해 설정된 MovingInfo를 받아와서 Monster를 만들어낸다.
 * 
 * @author Velmont
 * 
 */

public class MonsterPickManager {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private TimeManager timeManager;

	public Monster createMonsterByName(String Monstername) {
		Monster selectedMonster = unitAssets.getMonster(Monstername);
		unitManager.initiateMonster(selectedMonster);
		return selectedMonster;
	}

	public Monster createMonster(List<String> monsterList) {
		Random random = new Random();
		random.setSeed(timeManager.getSecondTime());
		String selectedMonsterName = monsterList.get(random.nextInt(monsterList.size()));
		return createMonsterByName(selectedMonsterName);
	}
}
