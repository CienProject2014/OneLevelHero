package com.mygdx.manager;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
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
	private NodeAssets nodeAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private DungeonManager dungeonManager;

	public Monster createMonsterByName(String Monstername) {
		Monster selectedMonster = unitAssets.getMonster(Monstername);
		unitManager.initiateMonster(selectedMonster);
		return selectedMonster;
	}

	public Monster createMonster(List<String> monsterList) {
		String selectedMonsterName = monsterList.get(ThreadLocalRandom.current().nextInt(monsterList.size()));
		return createMonsterByName(selectedMonsterName);
	}
}
