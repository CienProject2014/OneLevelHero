package com.mygdx.factory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.model.EventScene;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.MenuStage;
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
		if (stageName == "event") {
			return new EventStage();
		} else if (stageName == "village") {

			return new VillageStage();
		} else {
			return new MenuStage(stageName);
		}
	}

	public Stage makeStage(EventScene eventScene) {
		return new EventStage(eventScene);
	}

}
