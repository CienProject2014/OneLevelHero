package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.unit.Hero;

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
	@Autowired
	private BattleManager battleManager;

	private final int PROLOGUE_STORYSECTION_NUMBER = 101;

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setCurrentPosition();
		setStorySection();
		setTimeInfo();
		setBattleInfo();
		storySectionManager.runStorySequence();
	}

	private void setBattleInfo() {
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
	}

	private void setTimeInfo() {
		timeManager.setTime(1, 8, 0);
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
		// 마왕성에서부터 게임을 시작한다.
		positionManager.setCurrentNodeName("devil_castle");
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
