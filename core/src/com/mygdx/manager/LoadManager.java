package com.mygdx.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.Hero;
import com.mygdx.state.Assets;

@Component
public class LoadManager {
	@Autowired
	private Assets assets;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PartyInfo partyInfo;
	private Hero hero;

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setPartyList();
		setCurrentPosition();
	}

	public LoadManager() {
		Gdx.app.debug("LoadManager", "Constructor() call");
	}

	private void setCurrentPosition() {
		// Blackwood 마을에서부터 게임을 시작한다.
		positionInfo.setCurrentNode(WorldNodeEnum.BLACKWOOD.toString());
		positionInfo.setCurrentPlace(PlaceEnum.VILLAGE);

		// FIXME 초기 CurrentMoving 정보를 주입한다.
		movingInfo.setStartNode("Blackwood");
		movingInfo.setDestinationNode("Blackwood Forest");
		movingInfo.setRoadLength(7);
		movingInfo.setLeftRoadLength(7);
		List<String> monsterList = new ArrayList<String>();
		monsterList.add("assassin girl");
		movingInfo.setRoadMonsterList(monsterList);
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHero() {
		// 추후 JSON에서 불러오도록 바꿀 것
		setHero(assets.heroMap.get("yongsa"));
		this.hero.getStatus().setSpeed(8); // FIXME
	}

	// 해당 Hero들을 Party구성원에 포함시킨다
	private void setPartyList() {
		partyInfo.addHero(hero);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
