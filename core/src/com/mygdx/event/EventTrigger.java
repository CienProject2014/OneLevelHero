package com.mygdx.event;

import com.badlogic.gdx.utils.Json;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.unit.NPC;
import com.mygdx.util.EventManager;
import com.mygdx.util.ScreenManager;

public class EventTrigger {
	private static EventTrigger instance;
	private Json json;

	public static EventTrigger getInstance() {
		if (null == instance) {
			instance = new EventTrigger();
		}
		return instance;
	}

	public void initialize(OneLevelHero game) {
		ScreenManager.setGame(game);
	}

	private boolean event;

	public boolean hasEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public void makeEvent(String eventCode) {
		EventManager.getInstance().setEventCode(eventCode);
	}

	public NPC setNpcEvent(NPC npc) {
		json = new Json();
		Object jsonObject = Assets.parath;
		npc.setEvent(json.fromJson(Event.class, json.toJson(jsonObject)));
		return npc;
	}
}
