package com.mygdx.manager;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.CurrentPosition.CurrentMovingInfo;
import com.mygdx.model.Hero;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class LoadManager {
	private static LoadManager instance;
	private Hero hero;

	private LoadManager() {
		Assets.loadAll();
		setHero();
		setPartyList();
		setCurrentPosition();
	}

	private void setCurrentPosition() {
		//Blackwood 마을에서부터 게임을 시작한다.
		CurrentState.getInstance().getCurrentPosition()
				.setCurrentNode(WorldNodeEnum.BLACKWOOD.getNodeName());

		//FIXME 초기 CurrentMoving 정보를 주입한다.
		CurrentMovingInfo currentMovingInfo = CurrentState.getInstance()
				.getCurrentPosition().getCurrentMovingInfo();
		currentMovingInfo.setStartNode("Blackwood");
		currentMovingInfo.setDestinationNode("Blackwood Forest");
		currentMovingInfo.setRoadLength(7);
		currentMovingInfo.setLeftRoadLength(7);
		List<String> monsterList = new ArrayList();
		monsterList.add("assassin girl");
		currentMovingInfo.setRoadMonsterList(monsterList);
	}

	public static LoadManager getInstance() {
		if (null == instance) {
			instance = new LoadManager();
		}
		return instance;
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHero() {
		//추후 JSON에서 불러오도록 바꿀 것
		setHero(Assets.heroMap.get("yongsa"));
		//CurrentManager.getInstance().setHero(hero); // currentManager가 hero을 소유하도록 만든다.
		this.hero.getStatus().setSpeed(8);
	}

	// 해당 Hero들을 Party구성원에 포함시킨다
	private void setPartyList() {
		CurrentState.getInstance().getParty().addHero(hero);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
