package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.unit.Hero;
import com.mygdx.store.Loadable;

public class LoadManager {
	@Autowired
	private ApplicationContext applicationContext;
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

	private final int PROLOGUE_STORYSECTION_NUMBER = 101;

	@SuppressWarnings("unchecked")
	public void load() {
		Map<String, Loadable> loadedData = new HashMap<String, Loadable>();
		Kryo kryo = new Kryo();
		FileHandle handle = Gdx.files.internal("save/save_1.json");
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			for (Entry<String, Loadable> data : applicationContext.getBeansOfType(Loadable.class).entrySet()) {
				data.getValue().setData(kryo.readClassAndObject(input));
			}

			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setCurrentPosition();
		setStorySection();
		setTimeInfo();
		setBattleInfo();
		storySectionManager.runStorySequence();
		bagManager.possessItem(ItemEnum.WEAPON, "sabre");
		bagManager.possessItem(ItemEnum.WEAPON, "velmont_mouse"); // FIXME for
																	// Debug
		bagManager.possessItem(ItemEnum.WEAPON, "velmont_mouse");
	}

	private void setBattleInfo() {
		battleManager.setBeforePosition(PositionEnum.SUB_NODE);
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
	}

	private void setTimeInfo() {
		timeManager.setTime(1, 7, 0);
		partyManager.setFatigue(0);
	}

	public LoadManager() {
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
