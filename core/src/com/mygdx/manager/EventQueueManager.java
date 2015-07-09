package com.mygdx.manager;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.Event;

/**
 * 게임 분기마다 실행되는 트리거
 * 먼저 이벤트 큐에 있는 이벤트들을 순차적으로 실행한 후
 * 기존(마을,던전,갈림길,무빙,건물) 스크린으로 이동한다.
 * @author Velmont
 *
 */
public class EventQueueManager {
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionInfo positionInfo;

	public void runEventQueue() {
		Queue<Event> eventQueue = eventInfo.getEventQueue();
		if (!eventQueue.isEmpty()) {
			eventInfo.setCurrentEvent(eventQueue.peek());
			eventInfo.getClosedEventList().add(eventQueue.poll());
			screenFactory.show(ScreenEnum.EVENT);
		} else {
			goPreviousPlace();
		}
	}

	private void goPreviousPlace() {
		screenFactory.show(ScreenEnum.findScreenEnum(positionInfo
				.getCurrentPlace().toString()));
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

}
