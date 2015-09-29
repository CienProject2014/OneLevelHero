package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventPacket;

public class ChoiceNpcEventListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private SoundManager soundManager;
	private int index;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		EventPacket eventPacket = new EventPacket(eventManager.getCurrentNpc().getElementPath(), index);
		eventManager.triggerEvent(EventElementEnum.NPC, eventPacket);
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
