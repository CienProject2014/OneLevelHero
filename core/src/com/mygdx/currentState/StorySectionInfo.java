package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.model.NextSectionPacket;
import com.mygdx.model.StorySection;

public class StorySectionInfo {
	private HashMap<Integer, StorySection> achievedStorySectionMap = new HashMap<>();
	private ArrayList<NextSectionPacket> nextStorySectionList = new ArrayList<>();
	private int currentStorySectionNumber;
	private StorySection currentStorySection;

	public void saveCurrentStorySection() {
		//달성 맵에 현재 사용하던 분기 등록
		if (getCurrentStorySection() != null) {
			getAchievedStorySectionMap().put(
					this.getCurrentStorySectionNumber(),
					this.getCurrentStorySection());
		}
	}

	public int getCurrentStorySectionNumber() {
		return currentStorySectionNumber;
	}

	public void setCurrentStorySectionNumber(int currentStorySectionNumber) {
		this.currentStorySectionNumber = currentStorySectionNumber;
	}

	public StorySection getCurrentStorySection() {
		return currentStorySection;
	}

	public void setCurrentStorySection(StorySection currentStorySection) {
		this.currentStorySection = currentStorySection;
	}

	public HashMap<Integer, StorySection> getAchievedStorySectionMap() {
		return achievedStorySectionMap;
	}

	public void setAchievedStorySectionMap(
			HashMap<Integer, StorySection> achievedStorySectionMap) {
		this.achievedStorySectionMap = achievedStorySectionMap;
	}

	public ArrayList<NextSectionPacket> getNextStorySectionList() {
		return nextStorySectionList;
	}

	public void setNextStorySectionList(
			ArrayList<NextSectionPacket> nextStorySectionList) {
		this.nextStorySectionList = nextStorySectionList;
	}

}
