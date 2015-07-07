package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.model.EventPack;
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

	public void setCurrentStorySection(int storySectionNumber) {
		//기존 분기 저장 및 새 분기 설정
		saveAndGetNewStorySection(storySectionNumber);
		//분기에 정의된 이벤트를 이벤트 큐에 주입
		injectSectionEvent();
		injectNextSectionCondition();
		//이벤트 큐 실행
		eventQueueManager.runEventQueue();
	}

	public void goNextStorySection(String componentString) {
		checkNextStorySectionCondition(componentString);
	}

	private void checkNextStorySectionCondition(String componentString) {

	}

	private void saveAndGetNewStorySection(int storySectionNumber) {
		//기존 분기 저장
		storySectionInfo.saveCurrentStorySection();
		//새 분기 설정
		storySectionInfo.setCurrentStorySectionNumber(storySectionNumber);
		storySectionInfo.setCurrentStorySection(assets.storySectionMap
				.get(String.valueOf(storySectionNumber)));
	}

	//eventQueue에 이벤트들을 주입하고, eventState를 OPEN한다.
	private void injectSectionEvent() {
		Iterator<EventPack> currentStorySectionIterator = storySectionInfo
				.getCurrentStorySection().getEventList().iterator();
		while (currentStorySectionIterator.hasNext()) {
			eventManager.injectEvent(currentStorySectionIterator.next());
		}
	}

	private void injectNextSectionCondition() {
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
