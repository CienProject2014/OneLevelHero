package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.unit.Hero;

public class LoadNewManager {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private BagManager bagManager;
	@Autowired
	private EventManager eventManager;

	private final int PROLOGUE_STORYSECTION_NUMBER = 101;

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setCurrentPosition();
		setEventInfo(eventManager, eventAssets);
		setStorySection();
		setTimeInfo();
		setBattleInfo();
		storySectionManager.runStorySequence();
		bagManager.possessItem(ItemEnum.CONSUMABLES, "small_healing", 3);
		bagManager.possessItem(ItemEnum.HANDGRIP, "sabre");
		bagManager.possessItem(ItemEnum.HANDGRIP, "velmont_mouse"); // FIXME for
		bagManager.possessItem(ItemEnum.HANDGRIP, "velmont_mouse");
	}

	private void setEventInfo(EventManager eventManager, EventAssets eventAssets) {
		eventManager.setCurrentEventNpc("prologue");
		eventManager.setNpcMap(eventAssets.getNpcMap());
		eventManager.setGameObjectMap(eventAssets.getGameObjectMap());
	}

	private void setBattleInfo() {
		battleManager.setBeforePosition(PositionEnum.SUB_NODE);
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
	}

	private void setTimeInfo() {
		timeManager.setTime(1, 7, 0);
		partyManager.setFatigue(0);
	}

	public LoadNewManager() {
		Gdx.app.debug("LoadManager", "Constructor() call");
	}

	private void setStorySection() {
		StorySection prologueStorySection = eventAssets.getStorySection(PROLOGUE_STORYSECTION_NUMBER);

		storySectionManager.setCurrentStorySection(prologueStorySection);
		storySectionManager.insertStorySequence();
	}

	private void setCurrentPosition() {
		// 마왕성에서부터 게임을 시작한다.
		positionManager.setCurrentNodeName("devil_castle");
		positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHero() {
		// FIXME 추후 JSON에서 불러오도록 바꿀 것
		Hero yongsa = unitAssets.getHero("yongsa");
		Hero parath = unitAssets.getHero("parath");
		Hero lilis = unitAssets.getHero("lilis");
		partyManager.addHero(yongsa);
		partyManager.addHero(parath);
		partyManager.addHero(lilis);
	}
}
