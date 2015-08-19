package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.TimeInfo;

public class TimeManager {
	private final static int MINUTES_PER_HOUR = 60;
	private final static int HOURS_PER_DAY = 24;
	private final static int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;

	@Autowired
	private PartyManager partyManager;
	@Autowired
	private TimeInfo timeInfo;

	public void plusDay(int value) {
		plusHour(HOURS_PER_DAY * value);
	}

	public void plusHour(int value) {
		plusMinute(MINUTES_PER_HOUR * value);
	}

	public void plusMinute(int value) {
		Gdx.app.log("TimeManager", "Plus Minute : " + value);
		setTime(timeInfo.getTime() + value);
	}

	public void setTime(int time) {
		int beforeTime = getTime();
		timeInfo.setTime(time);
		int leftHour = (getTime() / MINUTES_PER_HOUR) - (beforeTime / MINUTES_PER_HOUR);
		if (leftHour >= 1) {
			partyManager.setFatigue(partyManager.getFatigue() + leftHour);
		}
	}

	public int getTime() {
		return timeInfo.getTime();
	}

	public void setTime(int day, int hour, int minute) {
		setTime(MINUTES_PER_DAY * day + MINUTES_PER_HOUR * hour + minute);
	}

	public int getDay() {
		return getTime() / MINUTES_PER_DAY;
	}

	public int getHour() {
		return (getTime() % MINUTES_PER_DAY) / MINUTES_PER_HOUR;
	}

	public int getMinute() {
		return getTime() % MINUTES_PER_HOUR;
	}

	public String getHourInfo() {
		if (getHour() > 12) {
			return "오후 " + (getHour() - 12) + "시 ";
		} else if (getHour() == 12) {
			return "오후 " + getHour() + "시 ";
		} else if (getHour() > 0) {
			return "오전 " + getHour() + "시 ";
		} else {
			return "오전 " + (getHour() + 12) + "시 ";
		}
	}

	public String getMinuteInfo() {
		if (getMinute() == 0) {
			return "00분";
		} else {
			return getMinute() + "분";
		}
	}

	public String getDayInfo() {
		return getDay() + "일째 ";
	}

	public String getTimeInfo() {
		return getDayInfo() + getHourInfo() + getMinuteInfo();
	}

}
