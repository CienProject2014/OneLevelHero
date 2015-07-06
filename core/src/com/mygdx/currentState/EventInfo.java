package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.model.EventPack;

public class EventInfo {
	// 현재 진행중인 이벤트 큐
	private Queue<EventPack> eventQueue = new LinkedList<>();
	// 진행이 끝난 이벤트 큐
	private Queue<EventPack> closedEventQueue = new LinkedList<>();

	public Queue<EventPack> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(Queue<EventPack> eventQueue) {
		this.eventQueue = eventQueue;
	}

	public Queue<EventPack> getClosedEventQueue() {
		return closedEventQueue;
	}

	public void setClosedEventQueue(Queue<EventPack> closedEventQueue) {
		this.closedEventQueue = closedEventQueue;
	}
}
