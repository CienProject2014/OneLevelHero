package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.StorySectionPacket;
import com.mygdx.model.surroundings.TargetTime;

public class EventCheckManager {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TimeManager timeManager;

	public boolean checkSameWithComponent(String eventComponent, String componentString) {
		return eventComponent.equals(componentString);
	}

	public boolean checkSameWithComponentList(List<String> eventComponentList, String componentString) {
		for (String eventComponent : eventComponentList) {
			if (eventComponent.equals(componentString)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkSameWithComponent(EventTypeEnum eventType, StorySectionPacket nextStorySectionPacket,
			String componentString) {
		switch (eventType) {
			case MOVE_FIELD :
			case MOVE_SUB_NODE :
			case MOVE_NODE :
			case BATTLE_CONTROL :
			case SELECT_EVENT :
			case CLICK_ARROW :
			case BATTLE_END :
				return checkMatchWithString(nextStorySectionPacket, componentString);
			case SELECT_COMPONENT :
				return checkSelectComponent(nextStorySectionPacket, componentString);
			case MOVE_SUB_NODE_BY_TIME :
				return checkMatchWithStringAndTime(nextStorySectionPacket, componentString);
			default :
				Gdx.app.log("EventCheckManager", "잘못된 EventCheckInfo오류");
				return checkMatchWithString(nextStorySectionPacket, componentString);
		}
	}

	public boolean checkMatchWithTime(TargetTime timeInfo) {
		int startMinute = timeInfo.getStartTime() * TimeManager.MINUTES_PER_HOUR;
		int endMinute = timeInfo.getEndTime() * TimeManager.MINUTES_PER_HOUR;
		int dayMinute = timeManager.getDayMinute();
		if (startMinute < endMinute) {
			if (dayMinute >= startMinute && dayMinute <= endMinute) {
				return false;
			} else {
				return true;
			}
		} else {
			if (dayMinute <= startMinute && dayMinute <= endMinute) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean checkMatchWithStringAndTime(StorySectionPacket nextSectionPacket, String componentString) {
		if (componentString.equals(nextSectionPacket.getTargetComponent())) {
			return checkMatchWithTime(nextSectionPacket.getTargetTime());
		} else {
			return false;
		}
	}

	private boolean checkSelectComponent(StorySectionPacket nextSectionPacket, String indexString) {
		if (eventManager.getCurrentElementEvent().getEventComponent().get(Integer.valueOf(indexString))
				.equals(nextSectionPacket.getTargetComponent())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSelectEvent(Event event) {
		if (event.getEventType().equals(EventTypeEnum.SELECT_COMPONENT)
				|| event.getEventType().equals(EventTypeEnum.SELECT_EVENT)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkMatchWithString(StorySectionPacket nextSectionPacket, String componentString) {
		if (componentString.equals(nextSectionPacket.getTargetComponent())) {
			return true;
		} else {
			return false;
		}
	}
}
