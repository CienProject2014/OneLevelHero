package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.StorySectionManager;

public class AttackButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_CONTROL,
				"normal_attack");
	}
}
