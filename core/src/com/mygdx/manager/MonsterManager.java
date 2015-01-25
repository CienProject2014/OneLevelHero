package com.mygdx.manager;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.model.Monster;
import com.mygdx.state.Assets;

/**
 * WorldMap에서 MovingInfoManager를 통해 설정된 MovingInfo를 받아와서 Monster를 만들어낸다.
 * @author Velmont
 *
 */

@Component
public class MonsterManager {
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	//FIXME 추후 몬스터 객체를 바로 받아오는 방법으로 바꾸자
	private List<String> monsterStringList;

	@PostConstruct
	public void init() {
		monsterStringList = movingInfo.getRoadMonsterList();
	}

	public void createMonster() {
		Monster selectedMonster = Assets.monsterMap.get(selectMonster());
		movingInfo.setSelectedMonster(selectedMonster);
	}

	private String selectMonster() {
		//랜덤하게 몬스터를 뽑아오는 로직 
		//FIXME 여기서는 인덱스0의 몬스터를 얻어오며 기획에 따라 수정하자
		String selectedMonsterString = monsterStringList.get(0);
		return selectedMonsterString;
	}
}
