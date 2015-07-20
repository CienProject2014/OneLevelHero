package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.TimeInfo;

public class TimeManager {
	@Autowired
	private TimeInfo timeInfo;

	private final static int MINUTES_PER_HOUR = 60;
	private final static int HOURS_PER_DAY = 24;
	private final static int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;

	public void adjustTimeDay(int value) {
		adjustTimeHour(HOURS_PER_DAY * value);
	}

	public void adjustTimeHour(int value) {
		adjustTimeMinute(MINUTES_PER_HOUR * value);
	}

	public void adjustTimeMinute(int value) {
		timeInfo.setTime(timeInfo.getTime() + value);
	}

	public void setTime(int day, int hour, int minute) {
		timeInfo.setTime(MINUTES_PER_DAY * getDay() + MINUTES_PER_HOUR
				* getHour() + getMinute());
	}

	public int getDay() {
		return timeInfo.getTime() / MINUTES_PER_DAY;
	}

	public int getHour() {
		return (timeInfo.getTime() % MINUTES_PER_DAY) / MINUTES_PER_HOUR;
	}

	public int getMinute() {
		return timeInfo.getTime() % MINUTES_PER_HOUR;
	}

}
