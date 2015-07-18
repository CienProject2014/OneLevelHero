package com.mygdx.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.EventPacket;
import com.mygdx.model.StorySection;
import com.mygdx.model.StorySectionPacket;

public class StorySectionManager {
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private UnitAssets unitAssets;
	private Queue<EventPacket> eventSequenceQueue = new LinkedList<>();

	public void setNewStorySectionAndPlay(int storyNumber) {
		setNewStorySection(storyNumber);
		insertStorySequence();
		runStorySequence();
	}

	public void setNewStorySection(int storyNumber) {
		storySectionInfo.setCurrentStorySection(storyNumber);
	}

	public List<StorySectionPacket> getNextSections() {
		return storySectionInfo.getCurrentStorySection().getNextSections();
	}

	public void insertStorySequence() {
		Gdx.app.log("StorySectionManager", "insertStorySequence");
		List<EventPacket> sequencialEvent = storySectionInfo
				.getCurrentStorySection().getSequencialEvents();
		for (EventPacket eventPacket : sequencialEvent) {
			eventSequenceQueue.add(eventPacket);
		}
	}

	public void runStorySequence() {
		Gdx.app.log("StorySectionManager", "runStorySequence");
		if (!eventSequenceQueue.isEmpty()) {
			EventPacket polledEventPacket = eventSequenceQueue.poll();
			eventManager.setCurrentEventInfo(polledEventPacket);
			switch (eventManager.getCurrentEvent().getEventType()) {
				case BATTLE:
					battleManager.startBattle(unitAssets
							.getMonster(eventManager.getCurrentEvent()
									.getEventComponent().get(0)));
					screenFactory.show(ScreenEnum.BATTLE);
					break;
				default:
					screenFactory.show(ScreenEnum.EVENT);
					break;
			}

		} else {
			goCurrentPlace();
		}
	}

	private void goCurrentPlace() {
		positionManager.goCurrentPlace();
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
