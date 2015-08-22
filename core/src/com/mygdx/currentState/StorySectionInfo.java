package com.mygdx.currentState;

import com.mygdx.model.event.StorySection;

public class StorySectionInfo {
	private StorySection currentStorySection;
	private int currentSectionNumber;

	public StorySection getCurrentStorySection() {
		return currentStorySection;
	}

	public void setCurrentStorySection(StorySection currentStorySection) {
		this.currentStorySection = currentStorySection;
	}

	public int getCurrentSectionNumber() {
		return currentSectionNumber;
	}

	public void setCurrentSectionNumber(int currentSectionNumber) {
		this.currentSectionNumber = currentSectionNumber;
	}
}
