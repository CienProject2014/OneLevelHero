package com.mygdx.model.event;

import com.mygdx.enums.QuestEnum;

public class Quest {
	private QuestEnum questType;
	private String questObjectName;
	private int questObjectCount;
	public QuestEnum getQuestType() {
		return questType;
	}
	public void setQuestType(QuestEnum questType) {
		this.questType = questType;
	}
	public String getQuestObjectName() {
		return questObjectName;
	}
	public void setQuestObjectName(String questObjectName) {
		this.questObjectName = questObjectName;
	}
	public int getQuestObjectCount() {
		return questObjectCount;
	}
	public void setQuestObjectCount(int questObjectCount) {
		this.questObjectCount = questObjectCount;
	}

}
