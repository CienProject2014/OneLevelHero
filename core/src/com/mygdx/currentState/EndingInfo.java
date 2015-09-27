package com.mygdx.currentState;

import java.util.Map;

public class EndingInfo {
	public Map<Integer, Boolean> getGameEndingInfo() {
		return gameEndingInfo;
	}
	public void setGameEndingInfo(Map<Integer, Boolean> gameEndingInfo) {
		this.gameEndingInfo = gameEndingInfo;
	}
	private Map<Integer, Boolean> gameEndingInfo;
}
