package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mygdx.model.Event;

public class EventInfo {
	// 현재 진행중인 이벤트 큐
	private Queue<Event> eventQueue = new LinkedList<>();
	// 진행이 끝난 이벤트 스택
	private List<Event> closedEventList = new ArrayList<>();
	private Event currentEvent;

	public Queue<Event> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(Queue<Event> eventQueue) {
		this.eventQueue = eventQueue;
	}

	public List<Event> getClosedEventList() {
		return closedEventList;
	}

	public void setClosedEventList(List<Event> closedEventList) {
		this.closedEventList = closedEventList;
	}

	public Event getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;
	}

}
