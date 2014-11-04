package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.model.NPC;
import com.mygdx.stage.EventStage;

public class EventManager {
	private static EventManager instance;

	public Stage makeStage(NPC currentNPC, int eventNumber) {
		//currentNPC와 eventNumber 매개변수로 stage를 만들어 리턴함.
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
