package com.mygdx.currentState;

public class TimeInfo {
	private int time;
	private int preTime;

	public int getTime() {
		return time / 60;
	}

	public int getSecondTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPreTime() {
		return preTime;
	}

	public void setPreTime(int preTime) {
		this.preTime = preTime;
	}
}
