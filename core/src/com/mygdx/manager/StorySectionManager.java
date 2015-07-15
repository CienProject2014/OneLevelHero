package com.mygdx.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.EventPacket;
import com.mygdx.model.StorySection;

public class StorySectionManager {
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionInfo positionInfo;
	private Queue<EventPacket> eventSequenceQueue = new LinkedList<>();

	public void insertStorySequence() {
		List<EventPacket> sequencialEvent = storySectionInfo
				.getCurrentStorySection().getSequencialEvents();
		for (EventPacket eventPacket : sequencialEvent) {
			eventSequenceQueue.add(eventPacket);
		}
	}

	public void runStorySequence() {
		if (!eventSequenceQueue.isEmpty()) {
			EventPacket polledEventPacket = eventSequenceQueue.poll();
			eventManager.setCurrentEventInfo(polledEventPacket);
			screenFactory.show(ScreenEnum.EVENT);
		} else {
			goPreviousPlace();
		}
	}

	private void goPreviousPlace() {
		screenFactory.show(ScreenEnum.findScreenEnum(positionInfo
				.getCurrentPlace().toString()));
	}

	public void setCurrentStorySection(StorySection storySection) {
		storySectionInfo.setCurrentStorySection(storySection);
	}

	public StorySection getCurrentStorySection() {
		return storySectionInfo.getCurrentStorySection();
	}

	public Queue<EventPacket> getSequenceQueue() {
		return eventSequenceQueue;
	}

	public void setSequenceQueue(Queue<EventPacket> sequenceQueue) {
		this.eventSequenceQueue = sequenceQueue;
	}
}
