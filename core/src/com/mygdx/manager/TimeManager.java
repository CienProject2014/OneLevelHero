package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.TimeInfo;

public class TimeManager {
	public final static int SECOND_PER_MINUTES = 60;
	public final static int MINUTES_PER_HOUR = 60;
	public final static int HOURS_PER_DAY = 24;
	public final static int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
	public final static int SECOND_PER_HOUR = MINUTES_PER_HOUR * SECOND_PER_MINUTES;
	public final static int SECOND_PER_DAY = MINUTES_PER_DAY * SECOND_PER_MINUTES;

	@Autowired
	private PartyManager partyManager;
	@Autowired
	private TimeInfo timeInfo;

	public void plusDay(int value) {
		Gdx.app.log("TimeManager", "Plus Day : " + value);
		plusHour(HOURS_PER_DAY * value);
	}

	public void plusHour(int value) {
		Gdx.app.log("TimeManager", "Plus Hour : " + value);
		plusMinute(MINUTES_PER_HOUR * value);
	}

	public void plusMinute(int value) {
		Gdx.app.log("TimeManager", "Plus Minute : " + value);
		plusSecond(SECOND_PER_MINUTES * value);
	}

	public void plusSecond(int value) {
		Gdx.app.log("TimeManager", "Plus Second : " + value);
		partyManager.getBattleMemberList().get(0).getStatus()
				.setExperience(partyManager.getBattleMemberList().get(0).getStatus().getExperience() - value);
		setTime(value);
	}

	public void setTime(int time) {
		int beforeTime = getTime();
		int beforeSecondTime = timeInfo.getSecondTime();
		timeInfo.setTime(beforeSecondTime + time);
		int leftHour = (getTime() / MINUTES_PER_HOUR) - (beforeTime / MINUTES_PER_HOUR);
		if (leftHour >= 1) {
			partyManager.setFatigue(partyManager.getFatigue() + leftHour);
		}
		partyManager.calculateLevel();
	}

	public int getTime() {
		return timeInfo.getTime();
	}

	public void setTime(int day, int hour, int minute) {
		timeInfo.setTime((MINUTES_PER_DAY * day + MINUTES_PER_HOUR * hour + minute) * SECOND_PER_MINUTES);
	}

	public int getSecondTime() {
		return timeInfo.getSecondTime();
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

	public int getDayMinute() {
		return getTime() % MINUTES_PER_DAY;
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

	public int getPreTime() {
		return timeInfo.getPreTime();
	}

	public void setPreTime(int preTime) {
		timeInfo.setPreTime(preTime);
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
