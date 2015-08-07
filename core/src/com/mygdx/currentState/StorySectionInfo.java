package com.mygdx.currentState;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.EventAssets;
import com.mygdx.model.event.StorySection;

public class StorySectionInfo {
	@Autowired
	private EventAssets eventAssets;

	private StorySection currentStorySection;

	public StorySection getCurrentStorySection() {
		return currentStorySection;
	}

	public void setCurrentStorySection(StorySection currentStorySection) {
		this.currentStorySection = currentStorySection;
	}

	public void setCurrentStorySection(int storyNumber) {
		currentStorySection = eventAssets.getStorySection(storyNumber);
	}
}
