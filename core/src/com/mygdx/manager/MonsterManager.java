package com.mygdx.manager;

import java.util.List;

import com.mygdx.model.Monster;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

/**
 * WorldMap에서 CurrentMovingManager를 통해 설정된 CurrentMovingInfo를 받아와서 Monster를 만들어낸다.
 * @author Velmont
 *
 */
public class MonsterManager {
	//FIXME 추후 몬스터 객체를 바로 받아오는 방법으로 바꾸자
	private static List<String> monsterStringList = CurrentState.getInstance()
			.getCurrentPosition().getCurrentMovingInfo().getRoadMonsterList();

	public static void createMonster() {
		Monster selectedMonster = Assets.monsterMap.get(selectMonster());
		CurrentState.getInstance().getCurrentPosition().getCurrentMovingInfo()
				.setSelectedMonster(selectedMonster);
	}

	private static String selectMonster() {
		//랜덤하게 몬스터를 뽑아오는 로직 
		//FIXME 여기서는 인덱스0의 몬스터를 얻어오며 기획에 따라 수정하자
		String selectedMonsterString = monsterStringList.get(0);
		return selectedMonsterString;
	}
}
