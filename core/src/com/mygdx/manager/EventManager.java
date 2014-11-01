package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.stage.EventStage;
import com.mygdx.unit.NPC;

public class EventManager {
	private static EventManager instance;

	public Stage makeStage(NPC currentNPC, int eventNumber) {
		EventStage stage = EventStage.getInstance().makeStage(currentNPC,
				eventNumber);
		return stage;
	}

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

}
