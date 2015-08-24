package com.mygdx.currentState;

public class TimeInfo {
	private int time;

	public int getTime() {
		return time / 60;
	}

	public int getSecondTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
