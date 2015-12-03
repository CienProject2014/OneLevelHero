package com.mygdx.model.battle;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.BattleManager;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.unit.Unit;

public class UseItemOnBattleCommand implements BattleCommand {
	@Autowired
	private BattleManager battleManager;

	@Override
	public void doCommand(Unit attackUnit, Unit defendUnit, int hitArea[][]) {
		if (battleManager.getCurrentSelectedItem() instanceof Consumables) {
			Consumables potion = (Consumables) battleManager.getCurrentSelectedItem();
			if ((attackUnit.getStatus().getHp() + potion.getHeal() < attackUnit.getStatus().getMaxHp())) {
				attackUnit.getStatus().setHp(attackUnit.getStatus().getHp() + potion.getHeal());
			} else {
				attackUnit.getStatus().setHp(attackUnit.getStatus().getMaxHp());
			}
		}

	}

}
