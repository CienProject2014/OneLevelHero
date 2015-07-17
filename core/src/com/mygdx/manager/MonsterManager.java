package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.UnitAssets;
import com.mygdx.model.Monster;

/**
 * WorldMap에서 MovingInfoManager를 통해 설정된 MovingInfo를 받아와서 Monster를 만들어낸다.
 *
 * @author Velmont
 *
 */

public class MonsterManager {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private MovingManager movingManager;

	public Monster createMonster() {
		return unitAssets.getMonster(selectMonster());
	}

	private String selectMonster() {
		// 랜덤하게 몬스터를 뽑아오는 로직
		// FIXME 여기서는 인덱스0의 몬스터를 얻어오며 기획에 따라 수정하자
		List<String> monsterStrings = movingManager.getRoadMonsters();
		String selectedMonsterString = monsterStrings.get(0);
		return selectedMonsterString;
	}
}
