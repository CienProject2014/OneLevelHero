package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.UnitAssets;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.event.EventParameters;

public class StartBattleEventTrigger implements EventTrigger {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		battleManager.setBackgroundPath(eventParameter.getBattle().getBackgroundPath());
		battleManager.setEventBattle(true);
		battleManager.startBattle(unitAssets.getMonster(eventParameter.getBattle().getTargetMonster()));
	}
}