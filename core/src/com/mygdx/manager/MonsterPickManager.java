package com.mygdx.manager;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.PositionEnum;
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

	public Monster createMonster() {
		Monster monster = unitAssets.getMonster(selectMonster());
		return monster;
	}

	public Monster createSpecificMoster(String Montername) {
		Monster monster = unitAssets.getMonster(Montername);
		return monster;
	}

	public Monster createMonster(String monsterName) {
		Monster monster = unitAssets.getMonster(monsterName);
		unitManager.initiateMonster(monster);
		return monster;
	}

	private String selectMonster() {
		List<String> monsterStrings = null;

		if (positionManager.getCurrentLocatePositionType().equals(PositionEnum.LocatePosition.FIELD)) {
			FieldTypeEnum fieldType = fieldManager.getFieldType();
			monsterStrings = nodeAssets.getMonsterFieldListByFieldType(fieldType);
		} else {
			monsterStrings = dungeonManager.getDungeonInfo().getCurrentFloor().getFloorMonsterList();
		}

		return monsterStrings.get(ThreadLocalRandom.current().nextInt(monsterStrings.size()));
	}
}
