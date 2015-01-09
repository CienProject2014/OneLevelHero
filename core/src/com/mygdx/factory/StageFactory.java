package com.mygdx.factory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.manager.PlatformResourceManager;
import com.mygdx.model.EventScene;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.VillageStage;

public class StageFactory {
	private static StageFactory instance;

	private StageFactory() {
	}

	public static StageFactory getInstance() {
		if (null == instance) {
			instance = new StageFactory();
		}
		return instance;
	}

	public Stage makeStage(String stageName) {
		PlatformResourceManager rm = new PlatformResourceManager();
		rm.initPlatformerResources();

		if (stageName == "event") {
			return new EventStage();
		} else if (stageName == "village") {
			return new VillageStage();
		} else if (stageName == "monster") {
			return new MonsterStage();
		} else if (stageName == "encount") {
			return new EncounterStage();
		} else if (stageName == "battle") {
			return new BattleStage(rm);
		} else {
			return new MenuStage(stageName);
		}
	}

	public Stage makeStage(EventScene eventScene) {
		return new EventStage(eventScene);
	}

}
