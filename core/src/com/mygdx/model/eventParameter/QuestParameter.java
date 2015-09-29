package com.mygdx.model.eventParameter;

public class QuestParameter extends Parameter {
	private String questName;
	private String questObjectPath;
	private int questObjectCount;
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
	public int getQuestObjectCount() {
		return questObjectCount;
	}
	public void setQuestObjectCount(int questObjectCount) {
		this.questObjectCount = questObjectCount;
	}
}
