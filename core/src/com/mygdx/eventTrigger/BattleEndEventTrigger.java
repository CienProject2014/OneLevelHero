package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class BattleEndEventTrigger implements EventTrigger {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
		battleManager.setEventBattle(false);
		storySectionManager.runStorySequence();
	}
}