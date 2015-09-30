package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.QuestEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventParameters;

public class CheckQuestEventTrigger implements EventTrigger {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		String questName = eventParameter.getQuest().getQuestName();
		if (eventManager.getEventInfo().getQuestMap().get(questName).getQuestState()
				.equals(QuestEnum.QuestState.CLEARD)) {
			Event event = eventManager.getEventInfo().getNpcMap()
					.get(eventParameter.getEventPacket().getEventElement())
					.getEvent(eventParameter.getEventPacket().getEventNumber());
			eventManager.setCurrentEvent(EventElementEnum.NPC, eventParameter.getEventPacket());
			eventManager.setCurrentChatScenes(event.getEventParameter().getEventScenes());
			screenFactory.show(ScreenEnum.CHAT_EVENT);
		} else {
			eventManager.setCurrentChatScenes(eventParameter.getEventScenes());
			screenFactory.show(ScreenEnum.CHAT_EVENT);
		}
	}
}
