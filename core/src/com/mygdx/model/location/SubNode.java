package com.mygdx.model.location;

import com.mygdx.nextSectionChecker.ArgumentChecker;

public class SubNode {
	private String subNodeName;
	private String subNodePath;
	private TargetTime targetTime;

	public boolean canGoBuilding(int currentMinute) {
		return ArgumentChecker.checkIsInTargetTime(targetTime, currentMinute);
	}

	public String getSubNodeName() {
		return subNodeName;
	}
	public void setSubNodeName(String subNodeName) {
		this.subNodeName = subNodeName;
	}
	public TargetTime getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(TargetTime targetTime) {
		this.targetTime = targetTime;
	}

	public String getSubNodePath() {
		return subNodePath;
	}

	public void setSubNodePath(String subNodePath) {
		this.subNodePath = subNodePath;
	}
}
