package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.model.EventPacket;
import com.mygdx.model.NextSectionPacket;
import com.mygdx.state.Assets;

public class StorySectionManager {
	@Autowired
	private Assets assets;
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private EventQueueManager eventQueueManager;

	public void runNewSection(int storySectionNumber) {
		//기존 분기 저장 및 새 분기 설정
		setNewSection(storySectionNumber);
		//이벤트 큐 실행
		eventQueueManager.runEventQueue();
		Gdx.app.log("StorySectionManager", "runEventQueue");
	}

	public void setNewSection(int storySectionNumber) {
		//기존 분기 저장
		storySectionInfo.saveCurrentStorySection();
		//새 분기 설정
		storySectionInfo.setCurrentStorySectionNumber(storySectionNumber);
		storySectionInfo.setCurrentStorySection(assets.storySectionMap
				.get(String.valueOf(storySectionNumber))); //FIXME 키값이 Integer타입인데도 String형태로 변환하지 않으면 받아올 수 없습니다.

		//분기에 정의된 이벤트를 이벤트 큐에 주입
		insertSectionEvent();
		insertNextSectionInfo();
	}

	//eventQueue에 이벤트들을 주입하고, eventState를 OPEN한다.
	private void insertSectionEvent() {
		Iterator<EventPacket> currentStorySectionIterator = storySectionInfo
				.getCurrentStorySection().getEventList().iterator();
		while (currentStorySectionIterator.hasNext()) {
			eventManager.insertEvent(currentStorySectionIterator.next());
		}
	}

	private void insertNextSectionInfo() {
		Iterator<NextSectionPacket> nextStorySectionListIterator = storySectionInfo
				.getCurrentStorySection().getNextSectionList().iterator();
		while (nextStorySectionListIterator.hasNext()) {
			NextSectionPacket nextSectionPacket = nextStorySectionListIterator
					.next();
			storySectionInfo.getNextStorySectionList().add(nextSectionPacket);
		}
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public StorySectionInfo getStorySectionInfo() {
		return storySectionInfo;
	}

	public void setStorySectionInfo(StorySectionInfo storySectionInfo) {
		this.storySectionInfo = storySectionInfo;
	}

	public EventQueueManager getEventQueueManager() {
		return eventQueueManager;
	}

	public void setEventQueueManager(EventQueueManager eventQueueManager) {
		this.eventQueueManager = eventQueueManager;
	}
}
