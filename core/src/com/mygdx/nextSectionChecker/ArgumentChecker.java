package com.mygdx.nextSectionChecker;

import com.badlogic.gdx.Gdx;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.location.TargetTime;

public class ArgumentChecker {
	public static boolean checkIsSame(Object arg1, Object arg2) {
		return arg1.equals(arg2);
	}

	public static boolean checkIsInTargetTime(TargetTime timeInfo, int currentMinute) {
		int startMinute = timeInfo.getStartHour() * TimeManager.MINUTES_PER_HOUR;
		int endMinute = timeInfo.getEndHour() * TimeManager.MINUTES_PER_HOUR;
		if (startMinute == 0 && endMinute == 0) {
			return true;
		}
		if (startMinute < endMinute) {
			if (currentMinute >= startMinute && currentMinute <= endMinute) {
				return false;
			} else {
				return true;
			}
		} else {
			if (currentMinute <= startMinute && currentMinute <= endMinute) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static boolean checkIsSameLocationInTargetTime(String location, int startHour, int endHour,
			int currentMinute, String args[]) {
		if (checkIsSame(location, args[0])) {
			int startMinute = startHour * TimeManager.MINUTES_PER_HOUR;
			int endMinute = endHour * TimeManager.MINUTES_PER_HOUR;
			if (startMinute < endMinute) {
				if (currentMinute >= startMinute && currentMinute <= endMinute) {
					return false;
				} else {
					return true;
				}
			} else {
				if (currentMinute <= startMinute && currentMinute <= endMinute) {
					return false;
				} else {
					return true;
				}
			}
		}
		Gdx.app.log("EventChecker", "args개수 오류");
		return false;
	}
}
