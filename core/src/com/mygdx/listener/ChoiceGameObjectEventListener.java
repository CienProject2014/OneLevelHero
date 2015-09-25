package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventPacket;

public class ChoiceGameObjectEventListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;
	private int index;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		EventPacket eventPacket = new EventPacket(eventManager.getCurrentGameObject().getFacePath(), index);
		eventManager.triggerEvent(EventElementEnum.GAME_OBJECT, eventPacket);
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
