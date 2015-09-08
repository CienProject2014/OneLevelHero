package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;

public class ChoiceOptionListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;
	private String targetOption;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		eventManager.triggerCurrentEvent();
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.CHOICE_OPTION, getTargetOption());
	}

	public String getTargetOption() {
		return targetOption;
	}

	public void setTargetOption(String targetOption) {
		this.targetOption = targetOption;
	}

}