package com.mygdx.model.eventParameter;

import com.mygdx.manager.TimeManager;

public class TimeParameter extends Parameter {
	private int day;
	private int hour;
	private int minute;
	private int startHour;
	private int endHour;
	public TimeParameter() {

	}

	public int getTimeConvertedByMinute() {
		return (day * TimeManager.MINUTES_PER_DAY) + (hour * TimeManager.MINUTES_PER_HOUR) + minute;
	}
	public TimeParameter(int day, int hour, int minute) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
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
