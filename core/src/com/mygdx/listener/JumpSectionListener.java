package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.StorySectionManager;

public class JumpSectionListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (storySectionManager.getCurrentStorySection().getNextSections() != null) {
			storySectionManager.setNewStorySectionAndPlay(storySectionManager
					.getCurrentStorySection().getNextSections().get(0)
					.getNextSectionNumber());
		}
	}
}
