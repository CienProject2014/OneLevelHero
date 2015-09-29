package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.GameObject;
import com.mygdx.screen.BuildingScreen;

public class RestButtonListener extends ClickListener {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private SoundManager soundManager;
	private GameObject gameObject;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		eventManager.getEventInfo().setCurrentEventElementType(EventElementEnum.GAME_OBJECT);
		eventManager.getEventInfo().setCurrentGameObjectEvent(
				new EventPacket(eventManager.getCurrentGameObject().getElementPath(), 1));
		eventManager.setCurrentChatScenes(eventManager.getCurrentGameObject().getEvent(1).getEventParameter()
				.getEventScenes());
		BuildingScreen.isClickPopup = false;
		screenFactory.show(ScreenEnum.CHAT_EVENT);
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
