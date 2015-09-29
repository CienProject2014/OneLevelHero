package com.mygdx.manager;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.QuestEnum;
import com.mygdx.enums.QuestEnum.QuestState;
import com.mygdx.model.event.Quest;

public class QuestManager {
	@Autowired
	private EventManager eventManager;

	public void addQuest(Quest quest) {
		eventManager.getEventInfo().getQuestMap().put(quest.getQuestName(), quest);
	}

	public void checkHuntQuest(String monsterPath) {
		Iterator<Entry<String, Quest>> questMapIterator = eventManager.getEventInfo().getQuestMap().entrySet()
				.iterator();
		while (questMapIterator.hasNext()) {
			Quest quest = questMapIterator.next().getValue();
			if (quest.getQuestState().equals(QuestState.CLEARD)) {
				continue;
			}
			if (quest.getQuestName().equals(monsterPath)) {
				quest.setQuestCounter(quest.getQuestCounter() + 1);
				checkClearQuest(quest);
			}
		}
	}

	private void checkClearQuest(Quest quest) {
		if (quest.isAchivedQuest()) {
			quest.setQuestState(QuestEnum.QuestState.CLEARD);
		}
	}
}
