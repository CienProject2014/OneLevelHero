package com.mygdx.currentState;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.eventParameter.TimeParameter;

public class StorySectionInfo {
	private StorySection currentStorySection;
	private int currentSectionNumber;
	private TimeParameter storyStartTime;

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

	public void setStoryStartTime(TimeManager timeManager) {
		int day = timeManager.getDay();
		int hour = timeManager.getHour();
		int minute = timeManager.getMinute();
		this.storyStartTime = new TimeParameter(day, hour, minute);
	}

	public TimeParameter getStoryStartTime() {
		return storyStartTime;
	}
}
