package com.mygdx.model.location;

public class TargetTime {
	private int endHour;
	private int startHour;

	public TargetTime() {

	}

	public TargetTime(int startHour, int endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
}
