package com.mygdx.model.event;

import com.mygdx.enums.QuestEnum;

public class Quest {
	private QuestEnum.QuestType questType;
	private QuestEnum.QuestState questState;
	private String questName;
	private int questObjectCount;
	private String questObjectPath;
	private int questCounter;

	public QuestEnum.QuestType getQuestType() {
		return questType;
	}
	public void setQuestType(QuestEnum.QuestType questType) {
		this.questType = questType;
	}
	public QuestEnum.QuestState getQuestState() {
		return questState;
	}
	public void setQuestState(QuestEnum.QuestState questState) {
		this.questState = questState;
	}
	public int getQuestObjectCount() {
		return questObjectCount;
	}
	public void setQuestObjectCount(int questObjectCount) {
		this.questObjectCount = questObjectCount;
	}
	public String getQuestObjectPath() {
		return questObjectPath;
	}
	public void setQuestObjectPath(String questObjectPath) {
		this.questObjectPath = questObjectPath;
	}
	public String getQuestName() {
		return questName;
	}
	public void setQuestName(String questName) {
		this.questName = questName;
	}
	public int getQuestCounter() {
		return questCounter;
	}
	public void setQuestCounter(int questCounter) {
		this.questCounter = questCounter;
	}
	public boolean isAchivedQuest() {
		return questObjectCount == questCounter;
	}
}
