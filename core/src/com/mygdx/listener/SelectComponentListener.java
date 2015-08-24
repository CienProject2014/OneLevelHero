package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;

public class SelectComponentListener extends ClickListener {
	private int index;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		eventManager.triggerComponentEvent(index);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.SELECT_COMPONENT,
				String.valueOf(index));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}