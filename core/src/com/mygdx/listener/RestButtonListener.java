package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.model.event.EventPacket;
import com.mygdx.screen.BuildingScreen;
import com.mygdx.screen.DungeonEntranceScreen;
import com.mygdx.screen.ForkScreen;

public class RestButtonListener extends ClickListener {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private SoundManager soundManager;
	private String position;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		soundManager.setSoundByPathAndPlay("notice_sleep");
		eventManager.getEventInfo().setCurrentEventElementType(EventElementEnum.GAME_OBJECT);
		eventManager.getEventInfo().setCurrentGameObjectEvent(
				new EventPacket(eventManager.getCurrentGameObject().getElementPath(), 1));
		eventManager.setCurrentChatScenes(eventManager.getCurrentGameObject().getEvent(1).getEventParameter()
				.getEventScenes());
		switch (position) {
			case "building" :
				BuildingScreen.isClickPopup = false;
				break;
			case "fork" :
				ForkScreen.isClickPopup = false;
				break;
			case "dungeon_entrance" :
				DungeonEntranceScreen.isClickPopup = false;
				break;
			default :
				Gdx.app.log("RestButtonListener", "position정보오류 - " + position);
				break;
		}

		screenFactory.show(ScreenEnum.CHAT_EVENT);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
