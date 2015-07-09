package com.mygdx.currentState;

public class TimeInfo {
	private int time;

	private final static int MINUTES_PER_HOUR = 60;
	private final static int HOURS_PER_DAY = 24;
	private final static int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;

	public void setTime(int day, int hour, int minute) {
		time = MINUTES_PER_DAY * day + MINUTES_PER_HOUR * hour + minute;
	}

	public int getDay() {
		return time / MINUTES_PER_DAY;
	}

	public int getHour() {
		return (time % MINUTES_PER_DAY) / MINUTES_PER_HOUR;
	}

	public int getMinute() {
		return time % MINUTES_PER_HOUR;
	}

	public void adjustTimeDay(int value) {
		adjustTimeHour(HOURS_PER_DAY * value);
	}

	public void adjustTimeHour(int value) {
		adjustTimeMinute(MINUTES_PER_HOUR * value);
	}

	public void adjustTimeMinute(int value) {
		time += value;
	}
}
