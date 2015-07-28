package com.mygdx.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.Hero;
import com.mygdx.model.StorySection;

public class LoadManager {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private FieldInfo movingInfo;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;

	private final int PROLOGUE_STORYSECTION_NUMBER = 101;

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setCurrentPosition();
		setStorySection();
		timeManager.setTime(1, 8, 0);
		storySectionManager.runStorySequence();
	}

	public LoadManager() {
		Gdx.app.debug("LoadManager", "Constructor() call");
	}

	private void setStorySection() {
		StorySection prologueStorySection = eventAssets
				.getStorySection(PROLOGUE_STORYSECTION_NUMBER);

		storySectionManager.setCurrentStorySection(prologueStorySection);
		storySectionManager.insertStorySequence();
	}

	private void setCurrentPosition() {
		// blackwood 마을에서부터 게임을 시작한다.
		positionManager.setCurrentNodeName(WorldNodeEnum.BLACKWOOD.toString());

		// FIXME 초기 CurrentMoving 정보를 주입한다.
		movingInfo.setStartNode("blackwood");
		movingInfo.setDestinationNode("blackwood_Forest");
		movingInfo.setRoadLength(7);
		movingInfo.setLeftRoadLength(7);
		List<String> monsterList = new ArrayList<String>();
		monsterList.add("assassin girl");
		movingInfo.setRoadMonsterList(monsterList);
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHero() {
		//FIXME 추후 JSON에서 불러오도록 바꿀 것
		Hero yongsa = unitAssets.getHero("yongsa");
		Hero parath = unitAssets.getHero("parath");
		Hero lilis = unitAssets.getHero("lilis");
		partyManager.addHero(yongsa);
		partyManager.addHero(parath);
		partyManager.addHero(lilis);

	}
}
