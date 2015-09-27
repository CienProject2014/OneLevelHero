package com.mygdx.model.eventParameter;

public class QuestParameter extends Parameter {
	private String questObjectPath;
	private String questObjectCount;
	public String getQuestObjectCount() {
		return questObjectCount;
	}
	public void setQuestObjectCount(String questObjectCount) {
		this.questObjectCount = questObjectCount;
	}
	public String getQuestObjectPath() {
		return questObjectPath;
	}
	public void setQuestObjectPath(String questObjectPath) {
		this.questObjectPath = questObjectPath;
	}
}
