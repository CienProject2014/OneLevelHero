package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.StorySectionPacket;

public class EventCheckManager {
	@Autowired
	private EventManager eventManager;

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
				return checkMatchWithString(nextStorySectionPacket, componentString);
			case SELECT_COMPONENT :
				return checkSelectComponent(nextStorySectionPacket, componentString);
			default :
				Gdx.app.log("EventCheckManager", "잘못된 EventCheckInfo오류");
				return false;
		}
	}

	private boolean checkSelectComponent(StorySectionPacket nextSectionPacket, String indexString) {
		if (eventManager.getCurrentNpcEvent().getEventComponent().get(Integer.valueOf(indexString))
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
