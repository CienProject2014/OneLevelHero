package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.EventScene;
import com.mygdx.stage.EventStage;

public class EventStageManager {
	private static EventStageManager instance;

	public Stage makeStage(EventScene eventScene) {
		return EventStage.getInstance().makeStage(eventScene,
				EventTypeEnum.CHAT);
	}

	public Stage makeStage(EventScene eventScene, EventTypeEnum eventType) {
		return EventStage.getInstance().makeStage(eventScene, eventType);
	}

	public static EventStageManager getInstance() {
		if (null == instance) {
			instance = new EventStageManager();
		}
		return instance;
	}

}
