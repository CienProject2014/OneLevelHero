package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.BattleSituationEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class EndBattleEventTrigger implements EventTrigger {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		battleManager.setCurrentBattleSituation(BattleSituationEnum.NOT_IN_BATTLE);
		battleManager.setEventBattle(false);
		storySectionManager.runStorySequence();
	}
}