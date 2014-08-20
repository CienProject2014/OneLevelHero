package com.mygdx.event;

import com.mygdx.game.OneLevelHero;
import com.mygdx.screen.EventScreen;
import com.mygdx.util.EventManager;

public class EventTrigger {
	OneLevelHero game;
	EventManager eventManager;

	EventTrigger(OneLevelHero game, String title) {
		this.game = game;
		game.eventManager = eventManager;
	}

	private boolean hasEvent;

	public boolean hasEvent() {
		return hasEvent;
	}

	public void makeEvent() {
		game.setScreen(new EventScreen(game, EventTypeEnum.CHAT, "blackwood-01-01"));
	}
}
