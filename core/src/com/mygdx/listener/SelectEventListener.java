package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;

public class SelectEventListener extends ClickListener {
	private int index;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	private final int PLUS_ONE = 1;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		storySectionManager.triggerSectionEvent(EventTypeEnum.SELECT_EVENT,
				String.valueOf(index + PLUS_ONE));
		eventManager.setCurrentEventNumber(index + PLUS_ONE);
		screenFactory.show(ScreenEnum.EVENT);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}