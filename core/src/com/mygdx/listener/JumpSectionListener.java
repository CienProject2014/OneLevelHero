package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;

public class JumpSectionListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		if (CurrentInfo.isAdminMode) {
			if (!storySectionManager.getCurrentStorySection().getNextSections().isEmpty()) {
				storySectionManager.setNewStorySectionAndPlay(storySectionManager.getCurrentStorySection()
						.getNextSections().get(0).getNextSectionNumber());
			}
		}
	}
}
