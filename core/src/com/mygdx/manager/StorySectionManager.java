package com.mygdx.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.EventAssets;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.event.StorySectionPacket;

public class StorySectionManager {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private StorySectionInfo storySectionInfo;
	private Queue<EventPacket> eventSequenceQueue = new LinkedList<>();

	public void setNewStorySectionAndPlay(int storyNumber) {
		setNewStorySection(storyNumber);
		Gdx.app.log("StorySectionManager", "현재 분기번호 [" + storyNumber
				+ "] 가동중-------------------------------------------------------------------------------");
		insertStorySequence();
		insertConditionalEvents();
		insertSpecialEvents();
		runStorySequence();
	}

	private void insertSpecialEvents() {
		if (storySectionInfo.getCurrentStorySection() == null) {
			return;
		}
		List<EventPacket> specialEventList = storySectionInfo.getCurrentStorySection().getSpecialEvents();
		if (specialEventList != null) {
			for (EventPacket eventPacket : specialEventList) {
				Event specialEvent = eventManager.getNpcEvent(eventPacket);
				if (!specialEvent.getEventState().equals(EventStateEnum.ALWAYS_OPEN)) {
					specialEvent.setEventState(EventStateEnum.OPENED);
				}
				eventManager.getSpecialEventQueue().add(eventPacket);
			}
		}
	}
	private void insertConditionalEvents() {
		if (storySectionInfo.getCurrentStorySection() == null) {
			return;
		}
		List<EventPacket> conditionalEvent = storySectionInfo.getCurrentStorySection().getConditionalEvents();
		if (conditionalEvent != null) {
			for (EventPacket eventPacket : conditionalEvent) {
				eventManager.getNpcEvent(eventPacket).setEventState(EventStateEnum.OPENED);
			}
		}
	}

	public void triggerSectionEvent(EventTypeEnum eventType, String componentString) {
		if (getNextSections() != null) {
			for (StorySectionPacket nextStorySectionPacket : getNextSections()) {
				if (eventType.equals(nextStorySectionPacket.getEventType())) {
					if (eventCheckManager.checkSameWithComponent(eventType, nextStorySectionPacket, componentString)) {
						setNewStorySectionAndPlay(nextStorySectionPacket.getNextSectionNumber());
						break;
					}
				}
			}
		}
	}

	public void insertStorySequence() {
		Gdx.app.log("StorySectionManager", "insertStorySequence");
		if (storySectionInfo.getCurrentStorySection() == null) {
			Gdx.app.log("StorySectionManager", "해당 분기의 sequencialEvents가 없습니다");
			return;
		}
		List<EventPacket> sequencialEvent = storySectionInfo.getCurrentStorySection().getSequencialEvents();
		for (EventPacket eventPacket : sequencialEvent) {
			eventSequenceQueue.add(eventPacket);
		}
	}

	public void runStorySequence() {
		Gdx.app.log("StorySectionManager", "runStorySequence");
		if (!eventSequenceQueue.isEmpty()) {
			EventPacket polledEventPacket = eventSequenceQueue.poll();
			eventManager.setCurrentEventInfo(polledEventPacket);
			eventManager.setEventOpen(eventManager.getCurrentEvent());
			eventManager.doStoryEvent(eventManager.getCurrentEvent().getEventType());
		} else {
			goCurrentPosition();
		}
	}

	public void setNewStorySection(int storyNumber) {
		storySectionInfo.setCurrentStorySection(eventAssets.getStorySection(storyNumber));
	}

	public List<StorySectionPacket> getNextSections() {
		return storySectionInfo.getCurrentStorySection().getNextSections();
	}

	private void goCurrentPosition() {
		movingManager.goCurrentPosition();
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
